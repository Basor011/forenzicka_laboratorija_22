package com.beginsecure.test.model;

public class Istrazivac {

    private int id;
    private String ime,prezime, datum_rodjenja;

    public Istrazivac(int id, String ime, String prezime, String datum_rodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodjenja = datum_rodjenja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(String datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    @Override
    public String toString() {
        return ime + " " + prezime +" " + id;
    }
}
