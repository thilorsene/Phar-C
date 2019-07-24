package com.example.firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Accueil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txt,txt2;
    ImageButton search;
    SpinnerDialog spdialog;
    List<String> med;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = findViewById(R.id.toolbar);


        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);
        Timer timer = new Timer();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.navopen,R.string.navclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        med = new ArrayList<>();
        med.add(0,"Bonjour");


        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spdialog.showSpinerDialog();
            }
        });

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_ajout);
        }

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                long date = System.currentTimeMillis();
                                DateFormat sdf = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE);
                                DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
                                String dateString = sdf.format(date);
                                String Heur = tf.format(date);
                                txt = findViewById(R.id.a_heur2);
                                txt2 = findViewById(R.id.a_heur);
                                txt.setText(dateString);
                                txt2.setText(Heur);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();


        Backendless.Persistence.of(Vente.class).find(queryBuilder, new AsyncCallback<List<Vente>>() {
            @Override
            public void handleResponse(List<Vente> response) {
                Defaults.ventes = new ArrayList<>();
                Defaults.ventes = response;
                Toast.makeText(Accueil.this, "chargée", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(Accueil.this, ""+fault, Toast.LENGTH_SHORT).show();

            }
        });

        Backendless.Persistence.of(Medicament.class).find(queryBuilder, new AsyncCallback<List<Medicament>>() {
            @Override
            public void handleResponse(List<Medicament> response) {

                Defaults.medicaments = response;
                Toast.makeText(Accueil.this, "chargée", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Accueil.this, ""+fault, Toast.LENGTH_SHORT).show();
            }
        });

        TimerTask tt =new TimerTask() {
            @Override
            public void run() {

                    for (i=0;i<Defaults.medicaments.size();i++){
                        med.add(Defaults.medicaments.get(i).getNom());
                    }

            }
        };
        timer.schedule(tt,8000);


        spdialog = new SpinnerDialog(Accueil.this, (ArrayList<String>) med,"Choisissez",R.style.DialogAnimations_SmileWindow);
        spdialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                try {

                    AlertDialog.Builder popup = new AlertDialog.Builder(Accueil.this);
                    popup.setTitle("--"+s+"--");

                    popup.setMessage("Prix: "+Defaults.medicaments.get(i-1).getPrix()+"\n Quantité disponible: "+Defaults.medicaments.get(i-1).getQuantite());
                    popup.show();
                }
                catch (Exception e){
                    Toast.makeText(Accueil.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId())
        {
            case R.id.nav_ajout:
                Intent i = new Intent(Accueil.this,MainActivity.class);
                startActivity(i);
                break;


            case R.id.nav_update:
                Intent u = new Intent(Accueil.this,Misajour.class);
                startActivity(u);
                break;

            case R.id.nav_historique:
                Intent h = new Intent(Accueil.this,HistoriqueMenu.class);
                startActivity(h);
                break;

            case R.id.nav_medicaments:
                Intent s = new Intent(Accueil.this,Stock.class);
                startActivity(s);
                break;

            case R.id.nav_vente:
                Intent v = new Intent(Accueil.this,Ajouter_Vente.class);
                startActivity(v);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
