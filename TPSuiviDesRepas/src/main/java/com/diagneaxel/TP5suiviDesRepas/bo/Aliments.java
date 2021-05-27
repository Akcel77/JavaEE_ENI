package com.diagneaxel.TP5suiviDesRepas.bo;

public class Aliments {

    //Attributs
    private int idRepas;
    private int id;
    private String aliment;

    /**
     * Constructor complet
     * @param idRepas reference idRepas index
     * @param id index
     * @param aliment l'aliment
     */
    public Aliments(int idRepas, int id, String aliment) {
        this.idRepas = idRepas;
        this.id = id;
        this.aliment = aliment;
    }

    /**
     * Constructor sans id
     * @param idRepas reference idRepas index
     * @param aliment l'aliment
     */
    public Aliments(int idRepas, String aliment) {
        this.idRepas = idRepas;
        this.aliment = aliment;
    }

    //GETTER

    public int getIdRepas() {
        return idRepas;
    }

    public int getId() {
        return id;
    }

    public String getAliment() {
        return aliment;
    }
}
