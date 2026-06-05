package com.beginsecure.test.model;

import com.beginsecure.test.Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private static Database instance;

    private ArrayList<Nalog> registrovaniKorisnici;
    private ArrayList<Istrazivac> istrazivaciList;
    private ArrayList<Sesija> sesijeList;
    private ArrayList<Forenzicka_Istraga> istrageList;
    private ArrayList<Sesija> filterList;
    private HashMap<Istrazivac, ArrayList<Sesija>> istrazivaciSesije;
    private HashMap<Forenzicka_Istraga,ArrayList<Izvodjenje>> istrageIzvodjenje;


    private Connection connection;
    private PreparedStatement query;

    private Database(){
        registrovaniKorisnici= new ArrayList<>();
        istrazivaciList=new ArrayList<>();
        sesijeList=new ArrayList<>();
        istrageList=new ArrayList<>();
        filterList=new ArrayList<>();
        istrazivaciSesije=new HashMap<>();
        istrageIzvodjenje=new HashMap<>();

        connection= DBConnection.getConnection();
        loadSQL();
    }

    private void loadSQL(){
        try {

            ResultSet resultSet;
            query= connection.prepareStatement("SELECT * FROM SESIJA");
            resultSet= query.executeQuery();
            sesijeList=loadSesije(resultSet);

            query = connection.prepareStatement(
                    "SELECT * FROM istrazivac i join kriminalisticki_tehnicar kt " +
                            "using (id_istrazivaca) join tim_izvodjaca ti using (id_istrazivaca)" +
                            "join izvodjenje using (id_izvodjenja) join sesija using (id_izvodjenja)  ");
            resultSet= query.executeQuery();
            istrazivaciSesije=dodeliSesije(resultSet);

            query= connection.prepareStatement(
                    "SELECT * FROM FORENZICKA_ISTRAGA JOIN IZVODJENJE USING(id_forenzicke_istrage)"
            );
            resultSet=query.executeQuery();
            istrageIzvodjenje=dodajForenziku(resultSet);

            query= connection.prepareStatement("CALL add_dokaz(\n" +
                    "    'Jug Kalina',\n" +
                    "    'Slika tragova krvi.',\n" +
                    "    16,\n" +
                    "    7,\n" +
                    "    6,\n" +
                    "    'otvoren',\n" +
                    "    5\n" +
                    ");");
            resultSet= query.executeQuery();
           // System.out.println(resultSet.toString());

            System.out.println(istrazivaciSesije);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLegal(Sesija sesija, Istrazivac istrazivac){
        try{
           return  istrazivaciSesije.get(istrazivac).contains(sesija);
        }catch(NullPointerException e){
            return false;
        }
    }

    private HashMap<Forenzicka_Istraga,ArrayList<Izvodjenje>> dodajForenziku(ResultSet resultSet){
        HashMap<Forenzicka_Istraga,ArrayList<Izvodjenje>> lista=new HashMap<>();
        int  id_izvodjenja, id_forenzicke_istrage, id_laboratorije;;
        String naziv_istrage, datum, status;

        try{
            while(resultSet.next()){
                id_forenzicke_istrage=resultSet.getInt("id_forenzicke_istrage");
                naziv_istrage=resultSet.getString("naziv");
                Forenzicka_Istraga fi =new Forenzicka_Istraga(id_forenzicke_istrage,naziv_istrage);

                id_izvodjenja=resultSet.getInt("id_izvodjenja");
                id_laboratorije=resultSet.getInt("id_laboratorije");
                datum=resultSet.getDate("datum_izvodjenja").toString();
                status=resultSet.getString("status_izvodjenja");
                Izvodjenje i=new Izvodjenje(id_izvodjenja,id_forenzicke_istrage,id_laboratorije,datum,status);

                lista.computeIfAbsent(fi,key -> new ArrayList<>()).add(i);

            }
        }catch(SQLException e){
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

    private HashMap<Istrazivac, ArrayList<Sesija>> dodeliSesije(ResultSet resultSet) {
        HashMap<Istrazivac, ArrayList<Sesija>> lista = new HashMap<>();
        String ime,prezime,datum,pocetak,kraj;
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

    public ArrayList<Sesija> getFilterList(Istrazivac istrazivac) {
        System.out.println(istrazivaciSesije);
        filterList=istrazivaciSesije.get(istrazivac);
       // System.out.println(istrazivaciSesije.get(istrazivac));
       return filterList;
    }
    public void deleteSession(Sesija sesija, Istrazivac istrazivac){
        sesijeList.remove(sesija);
        istrazivaciSesije.get(istrazivac).remove(sesija);
        try {
            PreparedStatement query;
            query= connection.prepareStatement("SELECT ID_SESIJE,ID_ALATA FROM utrosak_alata JOIN SESIJA USING (id_sesije)");
            query.execute();
            query= connection.prepareStatement("DELETE FROM utrosak_alata " +
                    "WHERE id_sesije=" + sesija.getId());
            query.execute();
            query= connection.prepareStatement("DELETE FROM sesija " +
                    "WHERE id_sesije="+sesija.getId());
            query.execute();
            query= connection.prepareStatement("SELECT * FROM utrosak_alata JOIN SESIJA USING (id_sesije)");
            query.execute();

        }catch(SQLException e ){
          e.printStackTrace();
        }

    }
    public void updateStatus(Izvodjenje izvodjenje, String newStatus){

        try{
            int id_izv= izvodjenje.getId_izvodjenja();
            PreparedStatement query;
                query= connection.prepareStatement("UPDATE izvodjenje " +
                    "SET status_izvodjenja='"+ newStatus +"' WHERE id_izvodjenja="+id_izv);
                query.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

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

    public HashMap<Istrazivac, ArrayList<Sesija>> getIstrazivaciSesije() {
        return istrazivaciSesije;
    }

    public HashMap<Forenzicka_Istraga, ArrayList<Izvodjenje>> getIstrageIzvodjenje() {
        return istrageIzvodjenje;
    }
}
