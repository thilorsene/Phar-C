package com.example.firebase2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Stock extends AppCompatActivity {

    TextView txt1,txt2,txt3;
    ListView lstv;

    MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        getSupportActionBar().setTitle("Stock");

        txt1 = (TextView)findViewById(R.id.txt_nom);
        txt2 = (TextView)findViewById(R.id.txt_prix);
        txt3 = (TextView)findViewById(R.id.txt_qte);

        lstv =(ListView)findViewById(R.id.medlist);




        DataQueryBuilder queryBuilder = DataQueryBuilder.create();

        Backendless.Persistence.of(Medicament.class).find(queryBuilder, new AsyncCallback<List<Medicament>>() {
            @Override
            public void handleResponse(List<Medicament> response) {

                Defaults.medicaments = response;
                adapter = new MyAdapter(Stock.this,Defaults.medicaments);
                lstv.setAdapter(adapter);

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Stock.this, "Error"+ fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}




