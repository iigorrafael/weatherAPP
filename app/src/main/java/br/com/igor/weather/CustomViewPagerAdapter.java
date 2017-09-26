package br.com.igor.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
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
import java.util.Random;

import br.com.igor.weather.model.Cidade;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Aluno on 12/09/2017.
 */

public class CustomViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Cidade[] cidades;
    private Integer[] backgroundImage = new Integer[]{R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
            R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13,
            R.drawable.image14, R.drawable.image15, R.drawable.image16, R.drawable.image17, R.drawable.image18,
            R.drawable.image19};

    public CustomViewPagerAdapter(Context context) {
        this.context = context;
        getCidades();
    }

    public void getCidades() {
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

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        Random rand = new Random();
        int n = rand.nextInt(19);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView temperatura = (TextView) view.findViewById(R.id.temperatura);
        TextView hora = (TextView) view.findViewById(R.id.hora);
        TextView descricao = (TextView) view.findViewById(R.id.descricao);

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
        TextView sunrise = (TextView) view.findViewById(R.id.textViewSunrise);
        TextView sunset = (TextView) view.findViewById(R.id.textViewSunset);
        TextView humidity = (TextView) view.findViewById(R.id.textViewHumidade);
        TextView wind = (TextView) view.findViewById(R.id.textViewWind);


        try {
//            preencheHashMap(position);
            getCidades();
            if (cidades != null) {
                Log.i("LOL", "ADAPTER REFRESH");
                Cidade cidade = (Cidade) cidades[position];
                Log.i("LOL", cidade.getNome());
                Log.i("LOL", cidade.getTime());

                imageView.setImageResource(backgroundImage[n]);
                Bitmap icon = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                Blurry.with(context)
                        .radius(1)
                        .sampling(1)
                        .color(Color.parseColor("#80000000"))
                        .async()
                        .from(icon)
                        .into(imageView);

                textView.setText(cidade.getNome());
                temperatura.setText(cidade.getTemp().toString() + "ºC");
                descricao.setText(cidade.getDescription());
                hora.setText("Atualizado às: " + cidade.getTime());

                dia1.setText(cidade.getWeekday1());
                dia2.setText(cidade.getWeekday2());
                dia3.setText(cidade.getWeekday3());
                dia4.setText(cidade.getWeekday4());
                dia5.setText(cidade.getWeekday5());

                max1.setText(cidade.getMax1()+"°C");
                max2.setText(cidade.getMax2()+"°C");
                max3.setText(cidade.getMax3()+"°C");
                max4.setText(cidade.getMax4()+"°C");
                max5.setText(cidade.getMax5()+"°C");

                min1.setText(cidade.getMin1()+"°C");
                min2.setText(cidade.getMin2()+"°C");
                min3.setText(cidade.getMin3()+"°C");
                min4.setText(cidade.getMin4()+"°C");
                min5.setText(cidade.getMin5()+"°C");

                wind.setText("Ventos: "+cidade.getWindSpeedy());
                Log.i("LOL", cidade.getWindSpeedy());
                humidity.setText("Humidade do ar: "+cidade.getHumidity()+"%");
                Log.i("LOL", cidade.getHumidity());
                sunrise.setText("Amanhecer: "+cidade.getSunrise());
                Log.i("LOL", cidade.getSunrise());
                sunset.setText("Por-do-sol: "+cidade.getSunset());
                Log.i("LOL", cidade.getSunset());

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
                }
                if (cidade.getCondition_slug().equals("storm")) {
                    imagem.setImageResource(R.drawable.storm);
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
