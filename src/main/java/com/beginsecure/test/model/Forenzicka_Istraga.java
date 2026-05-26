package com.beginsecure.test.model;

import java.util.Objects;

public class Forenzicka_Istraga {

    private int id;
    private String naziv;

    public Forenzicka_Istraga(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Forenzicka_Istraga that = (Forenzicka_Istraga) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
