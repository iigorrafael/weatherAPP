package br.com.igor.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.orm.SugarContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import br.com.igor.weather.model.Cidade;
import br.com.igor.weather.model.Result;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ViewPager viewPager;
    CustomViewPagerAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Cidade[] cidades;
    private Integer position;
    private Result retorno = null;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu_layout);

        SugarContext.init(this);
        buscaPorCidade();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new CustomViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // METODO PARA ADICIONAR CIDADES
                startActivity(new Intent(MainActivity.this, CadastrarCidade.class));
//                startActivity(new Intent(MainActivity.this, PesquisarCidade.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app_bar, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("LOL", query);
                Intent intent = new Intent(getApplicationContext(), PesquisarCidade.class);
                Bundle b = new Bundle();
                b.putString("key", query); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("LOL", newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        atualizarView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    private void buscaPorCidade() {
        Log.i("LOL", "START BUSCA");
        PrevisaoAPI previsao = new PrevisaoAPI();
        try{
            cidades = (Cidade[]) Cidade.listAll(Cidade.class).toArray(new Cidade[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (cidades != null) {
            previsao.execute();
        } else {
            startActivity(new Intent(MainActivity.this, CadastrarCidade.class));
        }

        //        String url = "https://api.hgbrasil.com/weather/?format=json&city_name="+"&key=3f0e0e0e";
    }

    private void atualizarView() {
        buscaPorCidade();
        viewPager.setAdapter(adapter);

    }

    public void btnDeleteCidade(View view) {
        try {
            cidades[viewPager.getCurrentItem()].delete();
            atualizarView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //** CLASSE DO ASYNCTAKS QUE IRÀ BUSCAR OS DADOS DA API***
    public class PrevisaoAPI extends AsyncTask<String, Integer, Result> {

        private static final String TAG = "PrevisaoAPI ";

        protected Result doInBackground(String... urls) {
            for (int i = 0; i < cidades.length; i++) {
                Log.i("LOL", "https://api.hgbrasil.com/weather/?format=json&city_name=" + cidades[i].getNome() + "&key=3f0e0e0e");
                try {
                    String line, newjson = "";
                    URL endereco = new URL("https://api.hgbrasil.com/weather/?format=json&city_name=" + cidades[i].getNome() + "&key=3f0e0e0e");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(endereco.openStream(), "UTF-8"));
                    while ((line = reader.readLine()) != null) {
                        newjson += line;
                    }
                    Gson gson = new Gson();
                    retorno = gson.fromJson(newjson, Result.class);
                    Log.i("LOL", retorno.getResults().getForecast().get(0).getWeekday());
                    cidades[i].setTemp(retorno.getResults().getTemp());
                    cidades[i].setTime(retorno.getResults().getTime());
                    cidades[i].setCurrently(retorno.getResults().getCurrently());
                    cidades[i].setDate(retorno.getResults().getDate());
                    cidades[i].setDescription(retorno.getResults().getDescription());
                    cidades[i].setCondition_slug(retorno.getResults().getCondition_slug());
                    cidades[i].save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return retorno;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            mSwipeRefreshLayout.setRefreshing(false);
//            viewPagerAdapter.notifyDataSetChanged();
//            viewPager.setAdapter(viewPagerAdapter);
//            viewPager.setCurrentItem(position);
        }
    }

}

//
//<ListView
//        android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:id="@+id/listaForecast"
//                android:layout_marginTop="14dp"
//                android:layout_below="@+id/periodo"
//                android:layout_centerHorizontal="true" />