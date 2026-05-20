package com.beginsecure.test.view;

import com.beginsecure.test.model.Forenzicka_Istraga;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainView extends HBox {

    private HBox container;
    private VBox VBforenzika, VBsesije, VBbuttons, VBbuttonsComp, VBistrazivac;
    private TableView<Forenzicka_Istraga> ISTRAGETV;
    private TableView<Sesija> SESIJELTV;
    private ComboBox<Istrazivac> istrazivaciComboBox;
    private Button statusBtn, obrisiBtn;
    private Label istragaLb, DbrLb, filterLb, sesijaLb;
    private CheckBox filterCheckBox;
    private Region spacer;

    private ObservableList<Forenzicka_Istraga> ISTRAGE ;
    private ObservableList<Sesija> SESIJE;
    private ObservableList<Istrazivac> ISTRAZIVACI;


    public MainView(Stage stage) {

        initElements();
        createTable();
        loadData();




    }

    private void initElements(){

        VBforenzika =new VBox();
        VBsesije = new VBox();
        VBbuttonsComp = new VBox();
        VBbuttons =new VBox();
        VBistrazivac=new VBox();
        container=new HBox();

        statusBtn= new Button();
        statusBtn.setText("Status");
        obrisiBtn=new Button();
        obrisiBtn.setText("Obrisi");

        istragaLb= new Label("Forenzicke istrage:");
        DbrLb= new Label("Dobro dosli!");
        filterLb= new Label("Prikazi sopstvene sesije");
        sesijaLb= new Label("Sesije:");

        ISTRAGETV= new TableView<Forenzicka_Istraga>();
        SESIJELTV = new TableView<Sesija>();
        istrazivaciComboBox= new ComboBox<Istrazivac>();
        filterCheckBox=new CheckBox("Prikazi samo sopstvene sesije");

      /// ISTRAGE= FXCollections.observableArrayList();

        VBbuttons.getChildren().addAll(statusBtn,obrisiBtn,filterCheckBox);
        filterCheckBox.setAlignment(Pos.BOTTOM_LEFT);
        VBbuttons.setAlignment(Pos.TOP_LEFT);
        VBbuttons.setSpacing(10);
        VBbuttons.setPadding(new Insets(25,0,0,0));

        VBforenzika.getChildren().addAll(istragaLb,ISTRAGETV);
        VBforenzika.setSpacing(10);

        VBsesije.getChildren().addAll(sesijaLb,SESIJELTV);
        VBsesije.setSpacing(10);

        container.getChildren().addAll(VBsesije,VBbuttons);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);

        VBistrazivac.getChildren().addAll(DbrLb,istrazivaciComboBox);
        VBistrazivac.setSpacing(10);
        VBistrazivac.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(VBforenzika, container,VBistrazivac);
        this.setSpacing(10);
        this.setPadding(new Insets(20,10,10,10));
        this.setAlignment(Pos.CENTER_LEFT);

    }

    private void loadData(){


    }
    private void createTable(){




    }
}
