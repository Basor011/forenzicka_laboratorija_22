package com.beginsecure.test.model;

import java.util.Objects;

public class Nalog {

    String ime, sifra;

    public Nalog(String ime, String sifra) {
        this.ime = ime;
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + sifra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nalog nalog = (Nalog) o;
        return Objects.equals(ime, nalog.ime) && Objects.equals(sifra, nalog.sifra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, sifra);
    }
}
