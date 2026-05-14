package com.beginsecure.test.model;

import java.util.ArrayList;

public class UserDatabase {

    private static UserDatabase instance;
    private ArrayList<Nalog> registrovaniKorisnici;

    private UserDatabase(){
        registrovaniKorisnici= new ArrayList<>();

    }

    public static UserDatabase getInstance() {

        if(instance== null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public ArrayList<Nalog> getRegistrovaniKorisnici() {
        return registrovaniKorisnici;
    }
}
