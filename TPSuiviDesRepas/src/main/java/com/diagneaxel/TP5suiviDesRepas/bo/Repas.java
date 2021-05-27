package com.diagneaxel.TP5suiviDesRepas.bo;

import java.util.List;

public class Repas {

    //Attributs
    private int id;
    private String date;
    private String heure;
    private List<Aliments> aliments;

    /**
     * Constructor sans id
     * @param date String date
     * @param heure String heure
     * @param aliments List Aliments
     */
    public Repas(String date, String heure, List<Aliments> aliments) {
        this.date = date;
        this.heure = heure;
        this.aliments = aliments;
    }

    /**
     * Constructor sans aliments
     * @param id index
     * @param date String date
     * @param heure String heure
     */
    public Repas(int id, String date, String heure) {
        this.id = id;
        this.date = date;
        this.heure = heure;
    }

    /**
     * Constructor sans aliments ni id
     * @param date String date
     * @param heure String heure
     */
    public Repas(String date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public List<Aliments> getAliments() {
        return aliments;
    }


    //SETTER
    public void setAliments(List<Aliments> aliments) {
        this.aliments = aliments;
    }
}
