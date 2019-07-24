package com.example.firebase2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Historique extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4;
    EditText total;
    ListView listh;
    List<Vente> liste;
    VenteAdapter adapter;
    Date date;
    int r=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        getSupportActionBar().setTitle("Historique des Ventes");

        liste = new ArrayList<>();
        txt1 = (TextView)findViewById(R.id.h_nom);
        txt2 = (TextView)findViewById(R.id.h_montant);
        txt3 = (TextView)findViewById(R.id.h_qte);
        txt4 = findViewById(R.id.total_t);
        total = findViewById(R.id.total);
            listh =(ListView)findViewById(R.id.h_liste);


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();


        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy 00:00:00");
        date = new Date(System.currentTimeMillis());

        Intent intent = getIntent();
        String message = intent.getStringExtra("choix");
        String mess = intent.getStringExtra("date");


        String strDate = sdf.format(date);

        if(message.equals("today")){
            getSupportActionBar().setTitle("Historique des Ventes du "+strDate.substring(0,11));
        //String where = "created before or at \'"+strDate+"\'";
       // queryBuilder.setWhereClause(where);
            int t =0;
            for(int i=0;i<Defaults.ventes.size();i++){
                if(Defaults.ventes.get(i).getCreated().substring(0,11).equals(strDate.substring(0,11))){
                    liste.add(Defaults.ventes.get(i));
                    t+= Defaults.ventes.get(i).getMontant();
                }

            }
            adapter = new VenteAdapter(Historique.this,liste);
            listh.setAdapter(adapter);
            total.setText(String.valueOf(t));

/*
        Backendless.Persistence.of(Medicament.class).find(queryBuilder, new AsyncCallback<List<Medicament>>() {
                @Override
                public void handleResponse(List<Medicament> response) {

                    Defaults.medicaments = response;
                    adapter = new VenteAdapter(Historique.this,Defaults.ventes);
                    listh.setAdapter(adapter);
                    Toast.makeText(Historique.this, "today", Toast.LENGTH_SHORT).show();
                    r=1;
                }

                @Override
                public void handleFault(BackendlessFault fault) {

                    Toast.makeText(Historique.this, "Error"+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            int t =0;
            for(int i=0;i<Defaults.ventes.size();i++){

                t+= Defaults.ventes.get(i).getMontant();

            }
            total.setText(String.valueOf(t));*/
        }

        if(message.equals("all")){
            getSupportActionBar().setTitle("Toutes les Ventes");

            Backendless.Persistence.of(Medicament.class).find(queryBuilder, new AsyncCallback<List<Medicament>>() {
                @Override
                public void handleResponse(List<Medicament> response) {

                    Defaults.medicaments = response;
                    adapter = new VenteAdapter(Historique.this,Defaults.ventes);
                    listh.setAdapter(adapter);
                }

                @Override
                public void handleFault(BackendlessFault fault) {

                    Toast.makeText(Historique.this, "Error"+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            int t =0;
            for(int i=0;i<Defaults.ventes.size();i++){

                t+= Defaults.ventes.get(i).getMontant();

            }
            total.setText(String.valueOf(t));
        }


        if(message.equals("oneday")){
            getSupportActionBar().setTitle("Historique Du "+mess.substring(1,11));
            //String where = "created = '"+mess+"'";
           // queryBuilder.setWhereClause(where);



            int t =0;
            for(int i=0;i<Defaults.ventes.size();i++){
                if(Defaults.ventes.get(i).getCreated().substring(0,10).equals(mess.substring(1,11))){
                    liste.add(Defaults.ventes.get(i));
                    t+= Defaults.ventes.get(i).getMontant();
                }

            }
            adapter = new VenteAdapter(Historique.this,liste);
            listh.setAdapter(adapter);
            total.setText(String.valueOf(t));
            Toast.makeText(this, ""+mess.substring(1,11)+" "+Defaults.ventes.get(1).getCreated().substring(0,10), Toast.LENGTH_LONG).show();
        }

        if(message.equals("mensuel")){
            int m ;
            String mois=null;
            switch (mess.substring(1,3)){
                case "01": mois ="Janvier";break;
                case "02": mois ="Fevrier";break;
                case "03": mois ="Mars";break;
                case "04": mois ="Avril";break;
                case "05": mois ="Mai";break;
                case "06": mois ="Juin";break;
                case "07": mois ="Juillet";break;
                case "08": mois ="Aout";break;
                case "09": mois ="Septembre";break;
                case "10": mois ="Octobre";break;
                case "11": mois ="Novembre";break;
                default: mois ="Decembre";
            }
            getSupportActionBar().setTitle("Historique Du mois de "+mois);
            //String where = "created = '"+mess+"'";
            // queryBuilder.setWhereClause(where);



            int t =0;
            for(int i=0;i<Defaults.ventes.size();i++){
                if(Defaults.ventes.get(i).getCreated().substring(0,2).equals(mess.substring(1,3))){
                    liste.add(Defaults.ventes.get(i));
                    t+= Defaults.ventes.get(i).getMontant();
                }

            }
            if(liste.isEmpty()){
                AlertDialog.Builder popup = new AlertDialog.Builder(Historique.this);
                popup.setTitle("Alerte");
                popup.setMessage("Aucune Vente enregistrée pour le mois de "+mois);
                popup.show();
            }
            adapter = new VenteAdapter(Historique.this,liste);
            listh.setAdapter(adapter);
            total.setText(String.valueOf(t));
            Toast.makeText(this, ""+mess.substring(1,3)+" "+Defaults.ventes.get(1).getCreated().substring(0,2), Toast.LENGTH_LONG).show();
        }


    }
    }

