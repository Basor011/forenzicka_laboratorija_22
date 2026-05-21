package com.beginsecure.test;

import com.beginsecure.test.Util.DBConnection;
import com.beginsecure.test.Util.FileUtil;
import com.beginsecure.test.model.Database;
import com.beginsecure.test.view.ChooseView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

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
        Connection connection = Database.getInstance().getConnection();
        if(connection != null){
            System.out.println("Baza je uspesno povezana!");
            /*
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACTOR");
                ResultSet resultSet= statement.executeQuery();
                while(resultSet.next()){

                    System.out.println(resultSet.getString(
                            "first_name")+ " "
                            + resultSet.getString("last_name") + " "
                            + resultSet.getInt("actor_id")
                    );


                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

             */
        }
        launch();

    }
}
