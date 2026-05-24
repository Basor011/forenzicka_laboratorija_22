package com.beginsecure.test.model;

import com.beginsecure.test.Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Database {

    private static Database instance;

    private ArrayList<Nalog> registrovaniKorisnici;
    private ArrayList<Istrazivac> istrazivaciList;
    private ArrayList<Sesija> sesijeList;
    private ArrayList<Forenzicka_Istraga> istrageList;
    private HashMap<Istrazivac, List<Sesija>> istrazivaciSesije;

    private Connection connection;
    private PreparedStatement query;

    private Database(){
        registrovaniKorisnici= new ArrayList<>();
        istrazivaciList=new ArrayList<>();
        sesijeList=new ArrayList<>();
        istrageList=new ArrayList<>();
        istrazivaciSesije=new HashMap<>();


        connection= DBConnection.getConnection();
        loadSQL();

    }

    private void loadSQL(){
        try {
            query = connection.prepareStatement("SELECT * FROM ISTRAZIVAC");
            ResultSet resultSet= query.executeQuery();
            istrazivaciList=loadIstrazivac(resultSet);

            query= connection.prepareStatement("SELECT * FROM SESIJA");
            resultSet= query.executeQuery();
            sesijeList=loadSesije(resultSet);


           query = connection.prepareStatement(
                    "SELECT * FROM istrazivac i join kriminalisticki_tehnicar kt " +
                            "using (id_istrazivaca) join tim_izvodjaca ti using (id_istrazivaca)" +
                            "join izvodjenje using (id_izvodjenja) join sesija using (id_izvodjenja)  ");
           resultSet= query.executeQuery();
          istrazivaciSesije=dodeliSesije(resultSet);

            System.out.println(istrazivaciSesije);


        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLegal(Sesija sesija, Istrazivac istrazivac){
        return istrazivaciSesije.get(istrazivac).contains(sesija);
    }


    private ArrayList<Istrazivac> loadIstrazivac(ResultSet resultSet){
        ArrayList<Istrazivac> lista= new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id;
                String ime, prezime, datum;

                ime = resultSet.getString("ime_istrazivaca");
                prezime = resultSet.getString("prezime_istrazivca");
                id = resultSet.getInt("id_istrazivaca");
                datum = resultSet.getDate("datum_rodjenja").toString();
                Istrazivac toADD = new Istrazivac(id, ime, prezime, datum);
                lista.add(toADD);
                System.out.println(toADD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private ArrayList<Sesija> loadSesije(ResultSet resultSet){
        ArrayList<Sesija> lista =new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id_s, id_i;
                String pocetak, kraj, datum;

                id_s = resultSet.getInt("id_sesije");
                id_i = resultSet.getInt("id_izvodjenja");
                pocetak = resultSet.getTime("vreme_pocetka").toString();
                kraj = resultSet.getTime("vreme_zavrsetka").toString();
                datum = resultSet.getDate("datum").toString();
                Sesija toADD = new Sesija(id_s, id_i, pocetak, kraj, datum);
                lista.add(toADD);

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    private HashMap<Istrazivac, List<Sesija>> dodeliSesije(ResultSet resultSet) {
        HashMap<Istrazivac, List<Sesija>> lista = new HashMap<>();
        String ime,prezime,datum,pocetak,kraj,datum_rodjenja;
        int id_istrazivaca,id_sesije,id_izvodjenja;


        try {
            while (resultSet.next()) {
                ime = resultSet.getString("ime_istrazivaca");
                prezime = resultSet.getString("prezime_istrazivca");
                id_istrazivaca = resultSet.getInt("id_istrazivaca");
                datum = resultSet.getDate("datum_rodjenja").toString();
                Istrazivac istrazivac = new Istrazivac(id_istrazivaca, ime, prezime, datum);

                id_sesije = resultSet.getInt("id_sesije");
                id_izvodjenja = resultSet.getInt("id_izvodjenja");
                pocetak = resultSet.getTime("vreme_pocetka").toString();
                kraj = resultSet.getTime("vreme_zavrsetka").toString();
                datum = resultSet.getDate("datum").toString();
                Sesija sesija = new Sesija(id_sesije, id_izvodjenja, pocetak, kraj, datum);

                lista.computeIfAbsent(istrazivac,key -> new ArrayList<>()).add(sesija);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public void deleteEntry(Sesija toDelete){


    }
    public static Database getInstance() {

        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<Nalog> getRegistrovaniKorisnici() {
        return registrovaniKorisnici;
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<Forenzicka_Istraga> getIstrageList() {
        return istrageList;
    }

    public ArrayList<Sesija> getSesijeList() {
        return sesijeList;
    }

    public ArrayList<Istrazivac> getIstrazivaciList() {
        return istrazivaciList;
    }
}
