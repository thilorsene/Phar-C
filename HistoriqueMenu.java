package com.example.firebase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class HistoriqueMenu extends AppCompatActivity {
EditText oneday,day2;
TextView txt1,txt2,txt3,txt4,txt5;
Button today,onedaybut,mensuel,all;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_menu);
        getSupportActionBar().setTitle("Menu de l'Historique");

        oneday = findViewById(R.id.oneday);
        day2 = findViewById(R.id.day2);

        txt1 = findViewById(R.id.trait);
        txt2 = findViewById(R.id.trait2);
        txt3 = findViewById(R.id.trait3);
        txt4 = findViewById(R.id.trait5);
        txt5 = findViewById(R.id.tilte);

        today = findViewById(R.id.today);
        onedaybut = findViewById(R.id.onedaybut);
        mensuel = findViewById(R.id.mensuel);
        all = findViewById(R.id.all);



        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoriqueMenu.this,Historique.class);
                i.putExtra("choix","all");
                startActivity(i);
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HistoriqueMenu.this,Historique.class);

                i.putExtra("choix","today");
                startActivity(i);
            }
        });
        onedaybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoriqueMenu.this,Historique.class);

                i.putExtra("choix","oneday");
                i.putExtra("date","a"+oneday.getText());
                startActivity(i);

            }
        });
        mensuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoriqueMenu.this,Historique.class);

                i.putExtra("choix","mensuel");
                i.putExtra("date","a"+day2.getText());
                startActivity(i);
            }
        });
    }


}
