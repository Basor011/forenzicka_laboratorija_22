package com.beginsecure.test.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ChooseView extends VBox {


    private Button loginBtn, registerBtn;
    private HBox hbox;

    public ChooseView(Stage stage){
        stage.setTitle("Biraj");
        loginBtn= new Button("Login");
        registerBtn= new Button("Register");

        loginBtn.setOnAction(e ->{
            Scene scene = new Scene(new LogInView(stage),400,250);
            stage.setScene(scene);
        });
        registerBtn.setOnAction(e ->{
            Scene scene = new Scene(new RegisterView(stage),400,250);
            stage.setScene(scene);
        });

        hbox= new HBox();
        hbox.getChildren().addAll(loginBtn,registerBtn);
        hbox.setAlignment(Pos.CENTER);


        this.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);
    }
}
