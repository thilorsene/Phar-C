package com.example.firebase2;

import java.util.Date;


    public class Medicament {
        private String nom;
        private int quantite;
        private  int prix;
        private Date created;
        private Date updated;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        private String objectId;





/*
        public Medicament(String nom, int prix, int quantite) {

            this.nom = nom;
            this.quantite = quantite;
            this.prix = prix;

        }
*/
        public Medicament(){
            this.nom =null;

        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public int getQuantite() {
            return quantite;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }

        public int getPrix() {
            return prix;
        }

        public void setPrix(int prix) {
            this.prix = prix;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public Date getUpdated() {
            return updated;
        }

        public void setUpdated(Date updated) {
            this.updated = updated;
        }
    }
