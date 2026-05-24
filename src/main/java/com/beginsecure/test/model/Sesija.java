package com.beginsecure.test.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "SESIJA " + id+"/"+id_izvodjenja;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sesija sesija = (Sesija) o;
        return id == sesija.id && id_izvodjenja == sesija.id_izvodjenja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_izvodjenja);
    }
}
