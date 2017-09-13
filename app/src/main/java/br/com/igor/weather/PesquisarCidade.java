package br.com.igor.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import br.com.igor.weather.model.Result;

/**
 * Created by Aluno on 12/09/2017.
 */

public class PesquisarCidade extends AppCompatActivity {

    private PrevisaoAPI api;
    private Result retorno = null;
    private Integer backgroundImage = R.drawable.verde;
    ImageView imageView;
    TextView textView;
    TextView temperatura;
    TextView hora;
    TextView descricao;
    ImageView imagem;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu_layout);

        imageView = (ImageView) findViewById(R.id.imageViewBackground);
        imageView.setImageResource(backgroundImage);
        textView = (TextView) findViewById(R.id.textViewCidade);
        temperatura = (TextView) findViewById(R.id.temperaturaPesquisa);
        hora = (TextView) findViewById(R.id.horaPesquisa);
        descricao = (TextView) findViewById(R.id.descricaoPesquisa);
        imagem = (ImageView) findViewById(R.id.imageViewTempoPesquisa);

        Bundle b = getIntent().getExtras();
        String value = null; // or other values
        if (b != null)
            value = b.getString("key");
        Log.i("LOL", value);
        api = new PrevisaoAPI();
        api.execute(value);
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
                api = new PrevisaoAPI();
                api.execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    class PrevisaoAPI extends AsyncTask<String, Integer, Result> {

        private static final String TAG = "PrevisaoAPI ";

        protected Result doInBackground(String... nome) {

            try {
                String line, newjson = "";
                Log.i("LOL", nome[0]);
                URL endereco = new URL("https://api.hgbrasil.com/weather/?format=json&city_name=" + nome[0] + "&key=3f0e0e0e");
                Log.i("LOL", endereco.toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(endereco.openStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    newjson += line;
                }
                Gson gson = new Gson();
                retorno = gson.fromJson(newjson, Result.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return retorno;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            try {
                Log.i("LOL", retorno.getResults().getTime());
                Log.i("LOL", retorno.getResults().getCity());
                Log.i("LOL", retorno.getResults().getCurrently());
                textView.setText(retorno.getResults().getCity_name());
                temperatura.setText(retorno.getResults().getTemp().toString() + "ºC");
                descricao.setText(retorno.getResults().getDescription());
                hora.setText("Atualizado às: " + retorno.getResults().getTime());
                Log.i("LOL", retorno.getResults().getCondition_slug());
                if (retorno.getResults().getCondition_slug().equals("clear_day")) {
                    imagem.setImageResource(R.drawable.clear_day);
                }
                if (retorno.getResults().getCondition_slug().equals("clear_night")) {
                    imagem.setImageResource(R.drawable.clear_night);
                }
                if (retorno.getResults().getCondition_slug().equals("cloud")) {
                    imagem.setImageResource(R.drawable.cloud);
                }
                if (retorno.getResults().getCondition_slug().equals("cloudly_night")) {
                    imagem.setImageResource(R.drawable.cloudly_night);
                }
                if (retorno.getResults().getCondition_slug().equals("cloudly_night")) {
                    imagem.setImageResource(R.drawable.cloudly_night);
                }
                if (retorno.getResults().getCondition_slug().equals("rain")) {
                    imagem.setImageResource(R.drawable.rain);
                } else if (retorno.getResults().getCondition_slug().equals("storm")) {
                    imagem.setImageResource(R.drawable.storm);
                } else {
                    Log.i("LOL", "else");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("LOL", e.toString());
            }
        }
    }
}


