package com.beginsecure.test.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "nemasifre";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Greska prilikom povezivanja na bazu!");
            e.printStackTrace();
            return null;
        }
    }
}
