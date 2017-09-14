package br.com.igor.weather;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
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
    Cidade[] cidadesBD;
    private Integer position;
    private Result retorno = null;
    GPSlocal gps;
    Location l;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu_layout);

        gps = new GPSlocal(getApplicationContext());
        l = gps.getLocation();
        if(l != null){
           Log.i("LOL", String.valueOf(l.getLatitude()));
        }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_settings was selected
            case R.id.btnDeleteCidade:
                // this is where you put your own code to do what you want.
                deleteCidade();
                break;
            default:
                break;
        }

        return true;
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
        PrevisaoAPIGPS gps = new PrevisaoAPIGPS();
        PrevisaoAPI previsao = new PrevisaoAPI();
        try {
            Cidade[] cidadesBD = (Cidade[]) Cidade.listAll(Cidade.class).toArray(new Cidade[0]);
            gps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cidades != null) {
            previsao.execute();
        }

        //        String url = "https://api.hgbrasil.com/weather/?format=json&city_name="+"&key=3f0e0e0e";
        //        https://api.hgbrasil.com/weather/?format=json&lat=-00.000&lon=-00.000&user_ip=200.17.101.69&key=3f0e0e0e
    }

    private void atualizarView() {
        buscaPorCidade();
        viewPager.setAdapter(adapter);

    }

    public void deleteCidade() {
        try {
            cidades[viewPager.getCurrentItem()].delete();
            atualizarView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public boolean checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission. ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission. ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                new AlertDialog().Builder(this)
//                        .setTitle(R.string.title_location_permissio)
//                        .setMessage(R.string.text_location_permission)
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //Prompt the user once explanation has been shown
//                                ActivityCompat.requestPermissions(MainActivity.this,
//                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                        MY_PERMISSIONS_REQUEST_LOCATION);
//                            }
//                        })
//                        .create()
//                        .show();
//
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission. ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // location-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission. ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, this);
//                    }
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//
//                }
//                return;
//            }
//
//        }
//    }


    //** CLASSE DO ASYNCTAKS QUE IRÀ BUSCAR OS DADOS DA API***
    public class PrevisaoAPI extends AsyncTask<String, Integer, Result> {

        private static final String TAG = "PrevisaoAPI ";

        protected Result doInBackground(String... urls) {
            for (int i = 1; i < cidades.length; i++) {
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

    //** CLASSE DO ASYNCTAKS QUE IRÀ BUSCAR OS DADOS DA API***
    public class PrevisaoAPIGPS extends AsyncTask<String, Integer, Result> {

        private static final String TAG = "PrevisaoAPI ";

        protected Result doInBackground(String... urls) {
                try {
                    String line, newjson = "";
                    URL endereco = new URL("https://api.hgbrasil.com/weather/?format=json&lat="+l.getLatitude()+"&lon="+l.getLongitude()+"&key=3f0e0e0e");
                    Log.i("LOL", "https://api.hgbrasil.com/weather/?format=json&lat="+l.getLatitude()+"&lon="+l.getLongitude()+"&key=3f0e0e0e");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(endereco.openStream(), "UTF-8"));
                    while ((line = reader.readLine()) != null) {
                        newjson += line;
                    }
                    Gson gson = new Gson();
                    retorno = gson.fromJson(newjson, Result.class);
                    Log.i("LOL", retorno.getResults().getForecast().get(0).getWeekday());
                    Cidade cid = new Cidade();
                    cid.setNome(retorno.getResults().getCity());
                    cid.setTemp(retorno.getResults().getTemp());
                    cid.setTime(retorno.getResults().getTime());
                    cid.setCurrently(retorno.getResults().getCurrently());
                    cid.setDate(retorno.getResults().getDate());
                    cid.setDescription(retorno.getResults().getDescription());
                    cid.setCondition_slug(retorno.getResults().getCondition_slug());
                    cidades[0] = cid;
                } catch (Exception e) {
                    e.printStackTrace();
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