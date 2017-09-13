package br.com.igor.weather;

import static br.com.igor.weather.Constants.FIRST_COLUMN;
import static br.com.igor.weather.Constants.SECOND_COLUMN;
import static br.com.igor.weather.Constants.THIRD_COLUMN;
import static br.com.igor.weather.Constants.FOURTH_COLUMN;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.igor.weather.model.Cidade;

/**
 * Created by Aluno on 12/09/2017.
 */

public class CustomViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Cidade[] cidades;
    private ListView listaForecast;
    private ArrayAdapter<Cidade> arrayAdapter;
    private ArrayList<HashMap<String, String>> list;
    private Integer position;
    private Integer backgroundImage = R.drawable.verde;

    public CustomViewPagerAdapter(Context context) {
        this.context = context;
        getCidades();
    }

    public void getCidades(){
        cidades = (Cidade[]) Cidade.listAll(Cidade.class).toArray(new Cidade[0]);
    }

    @Override
    public int getCount() {
        getCidades();
        return cidades.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
//        listaForecast = (ListView) view.findViewById(R.id.listaForecast);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        imageView.setImageResource(backgroundImage);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView temperatura = (TextView) view.findViewById(R.id.temperatura);
        TextView hora = (TextView) view.findViewById(R.id.hora);
        TextView descricao = (TextView) view.findViewById(R.id.descricao);
//        TextView periodo = (TextView) view.findViewById(R.id.periodo);
        ImageView imagem = (ImageView) view.findViewById(R.id.imageViewTempo);

        try {
//            preencheHashMap(position);
            getCidades();
            Log.i("LOL", "OUT");
            if (cidades != null) {
                Log.i("LOL", "ADAPTER REFRESH");
                Cidade cidade = (Cidade) cidades[position];
                try {
                    Log.i("LOL", cidade.getTime());
                    textView.setText(cidade.getNome());
                    temperatura.setText(cidade.getTemp().toString() + "ºC");
                    descricao.setText(cidade.getDescription());
                    hora.setText("Atualizado às: " + cidade.getTime());
//                    periodo.setText("Periodo: " + cidade.getCurrently());
                    Log.i("LOL", cidade.getCondition_slug());
                    if (cidade.getCondition_slug().equals("clear_day")) {
                        imagem.setImageResource(R.drawable.clear_day);
                    }
                    if (cidade.getCondition_slug().equals("clear_night")) {
                        imagem.setImageResource(R.drawable.clear_night);
                    }
                    if (cidade.getCondition_slug().equals("cloud")) {
                        imagem.setImageResource(R.drawable.cloud);
                    }
                    if (cidade.getCondition_slug().equals("cloudly_night")) {
                        imagem.setImageResource(R.drawable.cloudly_night);
                    }
                    if (cidade.getCondition_slug().equals("cloudly_night")) {
                        imagem.setImageResource(R.drawable.cloudly_night);
                    }
                    if (cidade.getCondition_slug().equals("rain")) {
                        imagem.setImageResource(R.drawable.rain);
                    } else if (cidade.getCondition_slug().equals("storm")) {
                        imagem.setImageResource(R.drawable.storm);
                    } else {
                        Log.i("LOL", "else");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("LOL", e.toString());
                }
            }
        } catch (Exception e) {
            Log.i("LOL", e.toString());
        }
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    private void preencheHashMap(int position){

        list = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(FIRST_COLUMN, cidades[position].getCondicaoDia1().getWeekday());
        temp.put(SECOND_COLUMN, cidades[position].getCondicaoDia1().getMin());
        temp.put(THIRD_COLUMN, cidades[position].getCondicaoDia1().getMax());
        temp.put(FOURTH_COLUMN, cidades[position].getCondicaoDia1().getDescription());
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put(FIRST_COLUMN, cidades[position].getCondicaoDia2().getWeekday());
        temp2.put(SECOND_COLUMN, cidades[position].getCondicaoDia2().getMin());
        temp2.put(THIRD_COLUMN, cidades[position].getCondicaoDia2().getMax());
        temp2.put(FOURTH_COLUMN, cidades[position].getCondicaoDia2().getDescription());
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(FIRST_COLUMN, cidades[position].getCondicaoDia3().getWeekday());
        temp3.put(SECOND_COLUMN, cidades[position].getCondicaoDia3().getMin());
        temp3.put(THIRD_COLUMN, cidades[position].getCondicaoDia3().getMax());
        temp3.put(FOURTH_COLUMN, cidades[position].getCondicaoDia3().getDescription());
        list.add(temp3);

        HashMap<String,String> temp4=new HashMap<String, String>();
        temp4.put(FIRST_COLUMN, cidades[position].getCondicaoDia4().getWeekday());
        temp4.put(SECOND_COLUMN, cidades[position].getCondicaoDia4().getMin());
        temp4.put(THIRD_COLUMN, cidades[position].getCondicaoDia4().getMax());
        temp4.put(FOURTH_COLUMN, cidades[position].getCondicaoDia4().getDescription());
        list.add(temp4);

        HashMap<String,String> temp5=new HashMap<String, String>();
        temp5.put(FIRST_COLUMN, cidades[position].getCondicaoDia5().getWeekday());
        temp5.put(SECOND_COLUMN, cidades[position].getCondicaoDia5().getMin());
        temp5.put(THIRD_COLUMN, cidades[position].getCondicaoDia5().getMax());
        temp5.put(FOURTH_COLUMN, cidades[position].getCondicaoDia5().getDescription());
        list.add(temp5);


        ListViewAdapter adapter = new ListViewAdapter(list);
        listaForecast.setAdapter(adapter);

    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
