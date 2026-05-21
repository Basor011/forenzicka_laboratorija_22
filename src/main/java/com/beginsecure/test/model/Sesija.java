package com.beginsecure.test.model;

public class Sesija {
    private int id, id_izvodjenja;
    private String pocetak,kraj,datum;


    public Sesija(int id,int id_izvodjenja, String pocetak, String kraj, String datum) {
        this.id = id;
        this.id_izvodjenja=id_izvodjenja;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public int getId_izvodjenja() {
        return id_izvodjenja;
    }

    public String getPocetak() {
        return pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public String getDatum() {
        return datum;
    }
}
