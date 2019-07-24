package com.example.firebase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MedInfo extends AppCompatActivity {

    ListView ls;
    VenteAdapter venteAdapter;
    List<Vente> Vfiltre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info);

        ls = findViewById(R.id.h_lview);


        venteAdapter = new VenteAdapter(MedInfo.this,Vfiltre);
    }
}
