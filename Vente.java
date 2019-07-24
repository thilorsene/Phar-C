package com.example.firebase2;

import java.util.Date;

public class Vente {

   private String id;
    private int qte;
    private int montant;
    private String nom_medoc;
    private String created;
    private String updated;
    private String objectId;

    public Vente(String id, int qte, String nom_medoc,int montant) {
        this.id = id;
        this.qte = qte;
        this.montant = montant;
        this.nom_medoc = nom_medoc;
    }
    public Vente(  String nom_medoc,int qte,int montant) {
        this.qte = qte;
        this.nom_medoc = nom_medoc;
        this.montant = montant;
    }

    public Vente(){}

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {

        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getNom_medoc() {
        return nom_medoc;
    }

    public void setNom_medoc(String nom_medoc) {
        this.nom_medoc = nom_medoc;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
