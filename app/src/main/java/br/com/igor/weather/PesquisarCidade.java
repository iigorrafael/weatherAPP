package br.com.igor.weather;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
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
import java.util.ArrayList;
import java.util.List;

import br.com.igor.weather.model.APIautocomplete;
import br.com.igor.weather.model.Cidade;
import br.com.igor.weather.model.Prediction;
import br.com.igor.weather.model.Result;

/**
 * Created by Aluno on 12/09/2017.
 */

public class PesquisarCidade extends AppCompatActivity {

    private PrevisaoAPI apiPrevisao;
    private AutocompleteAPI apiAutocomplete;
    private Result retorno = null;
    private APIautocomplete autocomplete = null;
    private Integer backgroundImage = R.drawable.verde;
    ImageView imageView;
    TextView textView;
    TextView temperatura;
    TextView hora;
    TextView descricao;
    ImageView imagem;
    TextView dia1;
    TextView dia2;
    TextView dia3;
    TextView dia4;
    TextView dia5;
    TextView max1;
    TextView max2;
    TextView max3;
    TextView max4;
    TextView max5;
    TextView min1;
    TextView min2;
    TextView min3;
    TextView min4;
    TextView min5;
    List<String> cidades;
    final List<String> suggestions = new ArrayList<>();

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
        dia1 = (TextView) findViewById(R.id.textViewDia1);
        dia2 = (TextView) findViewById(R.id.textViewDia2);
        dia3 = (TextView) findViewById(R.id.textViewDia3);
        dia4 = (TextView) findViewById(R.id.textViewDia4);
        dia5 = (TextView) findViewById(R.id.textViewDia5);
        max1 = (TextView) findViewById(R.id.textViewMaxDia1);
        max2 = (TextView) findViewById(R.id.textViewMaxDia2);
        max3 = (TextView) findViewById(R.id.textViewMaxDia3);
        max4 = (TextView) findViewById(R.id.textViewMaxDia4);
        max5 = (TextView) findViewById(R.id.textViewMaxDia5);
        min1 = (TextView) findViewById(R.id.textViewMinDia1);
        min2 = (TextView) findViewById(R.id.textViewMinDia2);
        min3 = (TextView) findViewById(R.id.textViewMinDia3);
        min4 = (TextView) findViewById(R.id.textViewMinDia4);
        min5 = (TextView) findViewById(R.id.textViewMinDia5);

        Bundle b = getIntent().getExtras();
        String value = null; // or other values
        if (b != null)
            value = b.getString("key");
        apiPrevisao = new PrevisaoAPI();
        apiPrevisao.execute(value);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app_bar, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);

        final SearchView searchView = (SearchView) item.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));



        final CursorAdapter suggestionAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{SearchManager.SUGGEST_COLUMN_TEXT_1},
                new int[]{android.R.id.text1},
                0);

        searchView.setSuggestionsAdapter(suggestionAdapter);

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery(suggestions.get(position), true);
                searchView.clearFocus();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apiPrevisao = new PrevisaoAPI();
                apiPrevisao.execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("LOL", newText+"0");
                Log.i("LOL", "1");
                apiAutocomplete = new AutocompleteAPI();
                Log.i("LOL", "2");
                apiAutocomplete.execute(newText);
                Log.i("LOL", "3");

                String[] columns = { BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1,
                        SearchManager.SUGGEST_COLUMN_INTENT_DATA,
                };
                Log.i("LOL", "4");
                MatrixCursor cursor = new MatrixCursor(columns);
                for (int i = 0; i < suggestions.size(); i++) {
                    String[] tmp = {Integer.toString(i),suggestions.get(i),suggestions.get(i)};
                    cursor.addRow(tmp);
                }
                Log.i("LOL", "4");
                suggestionAdapter.swapCursor(cursor);
                Log.i("LOL", "5");
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
                nome[0] = nome[0].replaceAll("\\s+", "");
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

                dia1.setText(retorno.getResults().getForecast().get(0).getWeekday());
                dia2.setText(retorno.getResults().getForecast().get(1).getWeekday());
                dia3.setText(retorno.getResults().getForecast().get(2).getWeekday());
                dia4.setText(retorno.getResults().getForecast().get(3).getWeekday());
                dia5.setText(retorno.getResults().getForecast().get(4).getWeekday());
                max1.setText(retorno.getResults().getForecast().get(0).getMax()+ "ºC");
                max2.setText(retorno.getResults().getForecast().get(1).getMax()+ "ºC");
                max3.setText(retorno.getResults().getForecast().get(2).getMax()+ "ºC");
                max4.setText(retorno.getResults().getForecast().get(3).getMax()+ "ºC");
                max5.setText(retorno.getResults().getForecast().get(4).getMax()+ "ºC");
                min1.setText(retorno.getResults().getForecast().get(0).getMin()+ "ºC");
                min2.setText(retorno.getResults().getForecast().get(1).getMin()+ "ºC");
                min3.setText(retorno.getResults().getForecast().get(2).getMin()+ "ºC");
                min4.setText(retorno.getResults().getForecast().get(3).getMin()+ "ºC");
                min5.setText(retorno.getResults().getForecast().get(4).getMin()+ "ºC");

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

    class AutocompleteAPI extends AsyncTask<String, Integer, APIautocomplete> {

        private static final String TAG = "AutocompleteAPI ";

        protected APIautocomplete doInBackground(String... nome) {

            try {
                String line, newjson = "";
                Log.i("LOL", nome[0]);
                nome[0] = nome[0].replaceAll("\\s+", "");
                URL endereco = new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + nome[0] + "&types=(cities)&language=fr&key=AIzaSyC1RBvPuvC3njCpKPNS4_4UCZeYv3D7ZRc");
                Log.i("LOL", endereco.toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(endereco.openStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    newjson += line;
                }
                Gson gson = new Gson();
                autocomplete = gson.fromJson(newjson, APIautocomplete.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return autocomplete;
        }

        @Override
        protected void onPostExecute(APIautocomplete autocomplete) {
            super.onPostExecute(autocomplete);
            try {
                Log.i("LOL", autocomplete.getPredictions().get(0).getDescription());
                Log.i("LOL", autocomplete.getPredictions().get(0).getTerms().get(0).getValue());
                for (Prediction prediction : autocomplete.getPredictions()) {
                    cidades.add(prediction.getTerms().get(0).getValue());

                }

                suggestions.clear();
                suggestions.addAll(cidades);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("LOL", e.toString());
            }
        }
    }
}


