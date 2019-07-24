package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static android.graphics.Color.RED;

public class MyAdapter extends ArrayAdapter<Medicament> {

private Context context;
private List<Medicament> listmed;

public MyAdapter(Context context,List<Medicament> listmed){
    super(context,R.layout.listform,listmed);
    this.context = context;
    this.listmed = listmed;
}


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listform,null);

        TextView txt1;
        txt1 = convertView.findViewById(R.id.txt_nom);
        TextView txt2;
        txt2 = convertView.findViewById(R.id.txt_prix);
        TextView txt3;
        txt3 = convertView.findViewById(R.id.txt_qte);

        txt1.setText(listmed.get(position).getNom());
        txt2.setText(String.valueOf(listmed.get(position).getPrix()));
        txt3.setText(String.valueOf(listmed.get(position).getQuantite()));
        if(listmed.get(position).getQuantite()<3){
            txt3.setBackgroundColor(RED);
            txt1.setBackgroundColor(RED);
            txt2.setBackgroundColor(RED);
        }

        return convertView;
    }
}
