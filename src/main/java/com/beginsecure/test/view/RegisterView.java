package com.beginsecure.test.view;

import com.beginsecure.test.Util.FileUtil;
import com.beginsecure.test.model.Nalog;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RegisterView extends VBox {

    private HBox HboxUser,HboxPass,HboxButtons;
    private VBox Vbox;
    private TextField username;
    private PasswordField password;
    private Button registerBtn,backBtn;
    private Label ime,sifra,registruj;

    public RegisterView(Stage stage){

        stage.setTitle("Registracija");
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

        registerBtn=new Button("Registruj");
        registerBtn.setOnAction(e-> {

        String ime = username.getText().trim();
        String sifra = password.getText().trim();
        Nalog nalog = new Nalog(ime,sifra);
        FileUtil.addAccount(nalog,"nalozi.txt");

        Scene scene = new Scene(new ChooseView(stage),200,150);
        stage.setScene(scene);
        stage.centerOnScreen();
        });

        ime=new Label("Ime: " );
        sifra= new Label("Sifra: ");
        registruj=new Label("Registrovanje korisnika");

        HboxUser.getChildren().addAll(ime,username);
        HboxUser.setAlignment(Pos.CENTER);
        HboxUser.setSpacing(5);

        HboxPass.getChildren().addAll(sifra,password);
        HboxPass.setAlignment(Pos.CENTER);
        HboxPass.setSpacing(5);

        HboxButtons.getChildren().addAll(registerBtn,backBtn);
        HboxButtons.setAlignment(Pos.CENTER);

        Vbox.getChildren().addAll(registruj,HboxUser,HboxPass,HboxButtons);
        Vbox.setAlignment(Pos.CENTER);
        Vbox.setSpacing(20);
        this.getChildren().addAll(Vbox);
        this.setAlignment(Pos.CENTER);
    }
}
