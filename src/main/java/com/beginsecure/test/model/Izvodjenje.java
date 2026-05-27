package com.beginsecure.test.model;

import java.util.Objects;

public class Izvodjenje {
   private int id_izvodjenja, id_forenzicke_istrage, id_laboratorije;
   private String datum,status;

    public Izvodjenje(int id_izvodjenja, int id_forenzicke_istrage, int id_laboratorije, String datum, String status) {
        this.id_izvodjenja = id_izvodjenja;
        this.id_forenzicke_istrage = id_forenzicke_istrage;
        this.id_laboratorije = id_laboratorije;
        this.datum = datum;
        this.status = status;
    }

    public int getId_izvodjenja() {
        return id_izvodjenja;
    }

    public int getId_forenzicke_istrage() {
        return id_forenzicke_istrage;
    }

    public int getId_laboratorije() {
        return id_laboratorije;
    }

    public String getDatum() {
        return datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Izvodjenje that = (Izvodjenje) o;
        return id_izvodjenja == that.id_izvodjenja;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_izvodjenja);
    }
}
