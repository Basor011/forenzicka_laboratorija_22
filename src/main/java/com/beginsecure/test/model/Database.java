package com.beginsecure.test.model;

import com.beginsecure.test.Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

    private static Database instance;

    private ArrayList<Nalog> registrovaniKorisnici;
    private ArrayList<Istrazivac> istrazivaciList;
    private ArrayList<Sesija> sesijeList;
    private ArrayList<Forenzicka_Istraga> istrageList;

    private Connection connection;
    private PreparedStatement query;

    private Database(){
        registrovaniKorisnici= new ArrayList<>();
        istrazivaciList=new ArrayList<>();
        sesijeList=new ArrayList<>();
        istrageList=new ArrayList<>();


        connection= DBConnection.getConnection();
        loadSQL();

    }

    private void loadSQL(){
        try {
            query = connection.prepareStatement("SELECT * FROM ISTRAZIVAC");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                int id;
                String ime, prezime, datum;

                ime=resultSet.getString("ime_istrazivaca");
                prezime=resultSet.getString("prezime_istrazivca");
                id=resultSet.getInt("id_istrazivaca");
                datum=resultSet.getDate("datum_rodjenja").toString();
                Istrazivac toADD= new Istrazivac(id,ime,prezime,datum);
                istrazivaciList.add(toADD);
                System.out.println(toADD);
            }

            query= connection.prepareStatement("SELECT * FROM SESIJA");
            resultSet= query.executeQuery();
            while(resultSet.next()){
                int id_s, id_i;
                String pocetak, kraj, datum;

                id_s=resultSet.getInt("id_sesije");
                id_i=resultSet.getInt("id_izvodjenja");
                pocetak=resultSet.getTime("vreme_pocetka").toString();
                kraj=resultSet.getTime("vreme_zavrsetka").toString();
                datum=resultSet.getDate("datum").toString();
                Sesija toADD=new Sesija(id_s,id_i,pocetak,kraj,datum);
                sesijeList.add(toADD);

            }



        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadFilteredSessions()

    public static Database getInstance() {

        if(instance== null) {
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
