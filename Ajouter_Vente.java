package com.example.firebase2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Ajouter_Vente extends AppCompatActivity {


    EditText txt1,txt2,txt3;

    Button btn1,btn2;
    ImageButton btn3;
    int i=0,q,b=1,a=0,c;
    List<String> medoc;
    List<Integer>medocPrix;

    Vente vente;

    SpinnerDialog spdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter__vente);

        getSupportActionBar().setTitle("Vente");

        txt1 = (EditText)findViewById(R.id.v_nom);

        btn2 = (Button)findViewById(R.id.add);
        txt2 = (EditText) findViewById(R.id.v_montant);
        btn3 =(ImageButton)findViewById(R.id.v_dialog);
        txt3 = (EditText)findViewById(R.id.v_qte);

        txt1.setEnabled(false);
        txt2.setEnabled(false);
         b = 1;



        medoc = new ArrayList<>();
        medocPrix = new ArrayList<>();

        medoc.add(0,"Bonjour");
        medocPrix.add(0,0);

        //Recuperation des Données : Nom et prix des Medicaments disponibles
            try{
                for (i=0;i<Defaults.medicaments.size();i++){
                    medoc.add(Defaults.medicaments.get(i).getNom());
                    medocPrix.add(Defaults.medicaments.get(i).getPrix());
                }

            }catch (Exception e){
                Toast.makeText(Ajouter_Vente.this, "eroor", Toast.LENGTH_SHORT).show();
            }

        txt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                b=1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    b = Integer.parseInt(txt3.getText().toString());
                    txt2.setText(String.valueOf(a*b));
                }
                catch (Exception e){
                    Toast.makeText(Ajouter_Vente.this, "Renseignez La quantité", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vente = new Vente(txt1.getText().toString(),Integer.parseInt(txt3.getText().toString()),Integer.parseInt(txt2.getText().toString()));
                int c = vente.getQte();
                if (Defaults.medicaments.get(q).getQuantite()<vente.getQte()){
                    AlertDialog.Builder popup = new AlertDialog.Builder(Ajouter_Vente.this);
                    popup.setTitle("Alerte");
                    popup.setMessage("Stock Insuffisant");
                    popup.show();
                }
                else {
                    Backendless.Persistence.save(vente, new AsyncCallback<Vente>() {


                        @Override
                        public void handleResponse(Vente response) {
                            Toast.makeText(Ajouter_Vente.this,"Ajout Effectif",Toast.LENGTH_SHORT).show();
                            txt1.setText("");
                            txt2.setText("");
                            txt3.setText("");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Ajouter_Vente.this,"error",Toast.LENGTH_SHORT).show();
                        }
                    });
                    int v =0;
                    for(int i=0;i<Defaults.medicaments.size();i++){
                        if(vente.getNom_medoc().equals(Defaults.medicaments.get(i).getNom())){
                            int r = Defaults.medicaments.get(i).getQuantite()-vente.getQte();
                            Defaults.medicaments.get(i).setQuantite(r);
                            v=1;
                            c=i;
                        }
                    }
                    if(v==1){
                    Backendless.Persistence.save(Defaults.medicaments.get(c), new AsyncCallback<Medicament>() {
                        @Override
                        public void handleResponse(Medicament response) {

                            Toast.makeText(Ajouter_Vente.this,"Ajout Effectif ",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Ajouter_Vente.this,fault +"error",Toast.LENGTH_LONG).show();
                        }
                    });
                    }
            }
            }});


                                    //  Instaciation et appel de la boite de recherche
        spdialog = new SpinnerDialog(Ajouter_Vente.this, (ArrayList<String>) medoc,"Choisissez",R.style.DialogAnimations_SmileWindow);
        spdialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                try {
                    txt1.setText(s);
                     a = medocPrix.get(i);
                     q=i-1;
                }
                catch (Exception e){
                    Toast.makeText(Ajouter_Vente.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spdialog.showSpinerDialog();
            }
        });

    }
}

