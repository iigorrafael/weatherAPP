package br.com.igor.weather;

import static br.com.igor.weather.Constants.FIRST_COLUMN;
import static br.com.igor.weather.Constants.SECOND_COLUMN;
import static br.com.igor.weather.Constants.THIRD_COLUMN;
import static br.com.igor.weather.Constants.FOURTH_COLUMN;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aluno on 12/09/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;

    public ListViewAdapter(ArrayList<HashMap<String, String>> list){
        super();
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.adapter_view_lista, null);

            txtFirst=(TextView) convertView.findViewById(R.id.dia);
            txtSecond=(TextView) convertView.findViewById(R.id.minima);
            txtThird=(TextView) convertView.findViewById(R.id.maxima);
            txtFourth=(TextView) convertView.findViewById(R.id.descricaoForecast);

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));
        txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }
}
