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
            query = connection.prepareStatement("SELECT * FROM ACTOR");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                int id;
                String ime,prezime, datum;
                ime=resultSet.getString("first_name");
                prezime=resultSet.getString("last_name");
                id=resultSet.getInt("actor_id");
                datum= "RANDOM";
                Istrazivac toADD= new Istrazivac(id,ime,prezime,datum);
                istrazivaciList.add(toADD);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

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
