package com.bindstone.kuljetus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@Document
public class TransportList {

    @Id
    private String numeroPVR;
    private String libelleMarque;
    private String designationCommerciale;
    private String couleur;

    public String getNumeroPVR() {
        return numeroPVR;
    }

    public void setNumeroPVR(String numeroPVR) {
        this.numeroPVR = numeroPVR;
    }

    public String getLibelleMarque() {
        return libelleMarque;
    }

    public void setLibelleMarque(String libelleMarque) {
        this.libelleMarque = libelleMarque;
    }

    public String getDesignationCommerciale() {
        return designationCommerciale;
    }

    public void setDesignationCommerciale(String designationCommerciale) {
        this.designationCommerciale = designationCommerciale;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "[", "]")
                .add("numeroPVR='" + numeroPVR + "'")
                .add("couleur='" + couleur + "'")
                .add("libelleMarque='" + libelleMarque + "'")
                .add("designationCommerciale='" + designationCommerciale + "'")
                .toString().concat("\n");
    }
}
