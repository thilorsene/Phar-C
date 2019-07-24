package com.example.firebase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Save;

    EditText nom,qte,prix;

    ListView list;
    ArrayList<Medicament> listemedoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Ajout de nouveaux produit");

        nom = (EditText)findViewById(R.id.nom);
        qte = (EditText)findViewById(R.id.qte);
        prix = (EditText)findViewById(R.id.prix);





        Save= (Button) findViewById(R.id.save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Medicament med = new Medicament();
                med.setNom(nom.getText().toString());
                med.setPrix(Integer.parseInt(prix.getText().toString()));
                med.setQuantite(Integer.parseInt(qte.getText().toString()));
                Backendless.Persistence.save(med, new AsyncCallback<Medicament>() {
                    @Override
                    public void handleResponse(Medicament response) {
                        Toast.makeText(MainActivity.this,"Ajout Effectif",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();


        Backendless.Persistence.of(Medicament.class).find(queryBuilder, new AsyncCallback<List<Medicament>>() {
            @Override
            public void handleResponse(List<Medicament> response) {

                Defaults.medicaments = response;
                Toast.makeText(MainActivity.this, "chargée", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {


            }
        });
    }


}

