package com.example.firebase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Misajour extends AppCompatActivity {


    EditText newQte,nom,rest;
    Button btn1;
    ImageButton btn2;
    SpinnerDialog spinnerDialog;
    List<String> medoc;
    List<Integer>medocQte;
    TimerTask timerTask;
    Timer t;
    int a,n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misajour);

        getSupportActionBar().setTitle("Renouvellement Stock");

        btn1 = (Button)findViewById(R.id.u_valider);
        btn2 = findViewById(R.id.u_cherche);
        newQte = (EditText)findViewById(R.id.u_qte);
        nom = (EditText)findViewById(R.id.u_nom);
        rest = (EditText)findViewById(R.id.u_reste);
        rest.setEnabled(false);
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();

        medocQte = new ArrayList<>();
        medoc = new ArrayList<>();

        medoc.add(0,"Aucun");
        medocQte.add(0,0);

                    int i;
                    for (i=0; i<Defaults.medicaments.size(); i++){
                        medoc.add(Defaults.medicaments.get(i).getNom());
                        medocQte.add(Defaults.medicaments.get(i).getQuantite());
                    }


        spinnerDialog = new SpinnerDialog(Misajour.this, (ArrayList<String>) medoc,"Choisissez",R.style.DialogAnimations_SmileWindow);
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                try {
                    nom.setText(s);
                    rest.setText(medocQte.get(i).toString());
                    a=i-1;

                }
                catch (Exception e){
                    Toast.makeText(Misajour.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n = Integer.parseInt(newQte.getText().toString());
                Defaults.medicaments.get(a).setQuantite(n);
                Backendless.Persistence.save(Defaults.medicaments.get(a), new AsyncCallback<Medicament>() {
                    @Override
                    public void handleResponse(Medicament response) {

                        Toast.makeText(Misajour.this,"Ajout Effectif "+n,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Misajour.this,fault +"error",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }
}
