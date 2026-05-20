package com.beginsecure.test.model;

public class Forenzicka_Istraga {

    int id;
    String naziv, status;

    public Forenzicka_Istraga(int id, String naziv, String status) {
        this.id = id;
        this.naziv = naziv;
        this.status=status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
