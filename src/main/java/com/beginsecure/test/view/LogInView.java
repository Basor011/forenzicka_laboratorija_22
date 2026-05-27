package com.beginsecure.test.view;

import com.beginsecure.test.model.Nalog;
import com.beginsecure.test.model.Database;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInView extends VBox {


    private HBox HboxUser,HboxPass,HboxButtons;
    private VBox Vbox;
    private TextField username;
    private PasswordField password;
    private Button logBtn, backBtn;
    private Label ime,sifra, loguj;

    public LogInView(Stage stage){

        stage.setTitle("Logovanje");
        HboxUser= new HBox();
        HboxPass= new HBox();
        HboxButtons= new HBox();
        Vbox= new VBox();

        username= new TextField();
        password=new PasswordField();

        backBtn= new Button("Nazad");
        backBtn.setOnAction(e->{
            Scene scene = new Scene(new ChooseView(stage),200,150);
            stage.setScene(scene);
        });

        logBtn=new Button("Loguj se");
        logBtn.setOnAction(e-> {
            String ime = username.getText().trim();
            String sifra = password.getText().trim();
            Nalog nalog = new Nalog(ime,sifra);

            if(Database.getInstance().getRegistrovaniKorisnici().contains(nalog)){
                System.out.println("Uspesno logovanje!");
                Scene scene= new Scene(new MainView(stage),1450,600);
                stage.setScene(scene);
                stage.centerOnScreen();

            }
            else{
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Neuspesno logovanje");
                alert.setHeaderText("Prijava neuspešna");
                alert.setContentText("Pogresna kombinacija imena i sifre");
                alert.showAndWait();
            }
        });

        ime=new Label("Ime: " );
        sifra= new Label("Sifra: ");
        loguj =new Label("Logovanje korisnika");

        HboxUser.getChildren().addAll(ime,username);
        HboxUser.setAlignment(Pos.CENTER);
        HboxUser.setSpacing(5);

        HboxPass.getChildren().addAll(sifra,password);
        HboxPass.setAlignment(Pos.CENTER);
        HboxPass.setSpacing(5);

        HboxButtons.getChildren().addAll(logBtn,backBtn);
        HboxButtons.setAlignment(Pos.CENTER);

        Vbox.getChildren().addAll(loguj,HboxUser,HboxPass,HboxButtons);
        Vbox.setAlignment(Pos.CENTER);
        Vbox.setSpacing(20);
        this.getChildren().addAll(Vbox);
        this.setAlignment(Pos.CENTER);

    }
}
