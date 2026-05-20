package com.beginsecure.test.model;

public class Sesija {
    int id, id_izvodjenja;
    String pocetak,kraj,datum;


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

    public void setId(int id) {
        this.id = id;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
