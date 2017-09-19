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

import com.orm.SugarContext;

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
        TextView dia1 = (TextView) view.findViewById(R.id.textViewDia1);
        TextView dia2 = (TextView) view.findViewById(R.id.textViewDia2);
        TextView dia3 = (TextView) view.findViewById(R.id.textViewDia3);
        TextView dia4 = (TextView) view.findViewById(R.id.textViewDia4);
        TextView dia5 = (TextView) view.findViewById(R.id.textViewDia5);
        TextView max1 = (TextView) view.findViewById(R.id.textViewMaxDia1);
        TextView max2 = (TextView) view.findViewById(R.id.textViewMaxDia2);
        TextView max3 = (TextView) view.findViewById(R.id.textViewMaxDia3);
        TextView max4 = (TextView) view.findViewById(R.id.textViewMaxDia4);
        TextView max5 = (TextView) view.findViewById(R.id.textViewMaxDia5);
        TextView min1 = (TextView) view.findViewById(R.id.textViewMinDia1);
        TextView min2 = (TextView) view.findViewById(R.id.textViewMinDia2);
        TextView min3 = (TextView) view.findViewById(R.id.textViewMinDia3);
        TextView min4 = (TextView) view.findViewById(R.id.textViewMinDia4);
        TextView min5 = (TextView) view.findViewById(R.id.textViewMinDia5);

        try {
//            preencheHashMap(position);
            getCidades();
            if (cidades != null) {
                Log.i("LOL", "ADAPTER REFRESH");
                Cidade cidade = (Cidade) cidades[position];
                Log.i("LOL", cidade.getNome());
                try {
                    Log.i("LOL", cidade.getTime());
                    textView.setText(cidade.getNome());
                    temperatura.setText(cidade.getTemp().toString() + "ºC");
                    descricao.setText(cidade.getDescription());
                    hora.setText("Atualizado às: " + cidade.getTime());
//                    periodo.setText("Periodo: " + cidade.getCurrently());
                    dia1.setText(cidade.getCondicaoDia1().getWeekday());
                    dia2.setText(cidade.getCondicaoDia2().getWeekday());
                    dia3.setText(cidade.getCondicaoDia3().getWeekday());
                    dia4.setText(cidade.getCondicaoDia4().getWeekday());
                    dia5.setText(cidade.getCondicaoDia5().getWeekday());
                    max1.setText(cidade.getCondicaoDia1().getMax());
                    max2.setText(cidade.getCondicaoDia2().getMax());
                    max3.setText(cidade.getCondicaoDia3().getMax());
                    max4.setText(cidade.getCondicaoDia4().getMax());
                    max5.setText(cidade.getCondicaoDia5().getMax());
                    min1.setText(cidade.getCondicaoDia1().getMin());
                    min2.setText(cidade.getCondicaoDia2().getMin());
                    min3.setText(cidade.getCondicaoDia3().getMin());
                    min4.setText(cidade.getCondicaoDia4().getMin());
                    min5.setText(cidade.getCondicaoDia5().getMin());

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
                    if (cidade.getCondition_slug().equals("cloudly_day")) {
                        imagem.setImageResource(R.drawable.cloudly_day);
                    }
                    if (cidade.getCondition_slug().equals("rain")) {
                        imagem.setImageResource(R.drawable.rain);
                    } else if (cidade.getCondition_slug().equals("storm")) {
                        imagem.setImageResource(R.drawable.storm);
                    } else {
                        imagem.setImageResource(R.drawable.clear_day);
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
