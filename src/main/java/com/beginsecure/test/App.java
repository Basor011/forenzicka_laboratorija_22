package com.beginsecure.test;

import com.beginsecure.test.model.DBConnection;
import com.beginsecure.test.model.FileUtil;
import com.beginsecure.test.view.ChooseView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(new ChooseView(stage),200,150);
        stage.setTitle("Biraj");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FileUtil.loadAccounts("nalozi.txt");
        Connection connection = DBConnection.getConnection();
        if(connection != null){
            System.out.println("Baza je uspesno povezana!");
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet= statement.executeQuery("SELECT * FROM ACTOR");
                while(resultSet.next()){
                    System.out.println(resultSet.getString(
                            "first_name")+ " "
                            + resultSet.getString("last_name")
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        launch();

    }
}
