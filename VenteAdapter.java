package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class VenteAdapter extends ArrayAdapter<Vente> {


    private Context context;
    private List<Vente> listvente;

    public VenteAdapter(Context context,List<Vente> listvente){
        super(context,R.layout.listform,listvente);
        this.context = context;
        this.listvente = listvente;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listform,null);

        TextView txt1;
        txt1 = convertView.findViewById(R.id.txt_nom);
        TextView txt2;
        txt2 = convertView.findViewById(R.id.txt_prix);
        TextView txt3;
        txt3 = convertView.findViewById(R.id.txt_qte);

        txt1.setText(listvente.get(position).getNom_medoc());
        txt3.setText(String.valueOf(listvente.get(position).getMontant()));
        txt2.setText(String.valueOf(listvente.get(position).getQte()));

        return convertView;
    }
}

