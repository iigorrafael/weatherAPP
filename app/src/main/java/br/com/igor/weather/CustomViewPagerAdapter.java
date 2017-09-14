package br.com.igor.weather;

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
