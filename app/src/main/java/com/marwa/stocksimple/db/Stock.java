package com.marwa.stocksimple.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Stock {



    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nomProduit;
    private String prix;
    private int quantite;
    private String fournisseur;
    private String fournisseurTel;
    private String fournisseurEmail;
    private String image;


    public Stock() {
    }

    @Ignore
    public Stock(String nomProduit, String prix, int quantite, String fournisseur, String fournisseurTel, String fournisseurEmail, String image) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.fournisseurTel = fournisseurTel;
        this.fournisseurEmail = fournisseurEmail;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getFournisseurTel() {
        return fournisseurTel;
    }

    public void setFournisseurTel(String fournisseurTel) {
        this.fournisseurTel = fournisseurTel;
    }

    public String getFournisseurEmail() {
        return fournisseurEmail;
    }

    public void setFournisseurEmail(String fournisseurEmail) {
        this.fournisseurEmail = fournisseurEmail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", nomProduit='" + nomProduit + '\'' +
                ", prix='" + prix + '\'' +
                ", quantite=" + quantite +
                ", fournisseur='" + fournisseur + '\'' +
                ", fournisseurTel='" + fournisseurTel + '\'' +
                ", fournisseurEmail='" + fournisseurEmail + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.id == ((Stock) obj).getId())
            return true;
        return false;
    }
}
