package com.beginsecure.test.view;

import com.beginsecure.test.model.Database;
import com.beginsecure.test.model.Forenzicka_Istraga;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainView extends HBox {

    private HBox container;
    private VBox VBforenzika, VBsesije, VBbuttons, VBbuttonsComp, VBistrazivac;
    private TableView<Forenzicka_Istraga> TVistrage;
    private TableView<Sesija> TVSesije;
    private ComboBox<Istrazivac> istrazivaciComboBox;
    private Button statusBtn, obrisiBtn;
    private Label istragaLb, DbrLb, sesijaLb;
    private CheckBox filterCheckBox;


    private ObservableList<Forenzicka_Istraga> listForenzickaIstraga ;
    private ObservableList<Sesija> listSesije;
    private ObservableList<Istrazivac> listIstrazivaci;


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
        sesijaLb= new Label("Sesije:");

        TVistrage = new TableView<Forenzicka_Istraga>();
        TVSesije = new TableView<Sesija>();

        istrazivaciComboBox= new ComboBox<>();
        listIstrazivaci = FXCollections.observableArrayList(Database.getInstance().getIstrazivaciList());
        istrazivaciComboBox.setItems(listIstrazivaci);
        istrazivaciComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, stari, novi) -> {
                    if (novi != null) {
                        System.out.println("Odabran: " + novi);

                    }
                }
        );

        filterCheckBox=new CheckBox("Prikazi sopstvene sesije");

        VBbuttons.getChildren().addAll(statusBtn,obrisiBtn,filterCheckBox);
        filterCheckBox.setAlignment(Pos.BOTTOM_LEFT);
        VBbuttons.setAlignment(Pos.TOP_LEFT);
        VBbuttons.setSpacing(10);
        VBbuttons.setPadding(new Insets(25,0,0,0));

        VBforenzika.getChildren().addAll(istragaLb, TVistrage);
        VBforenzika.setSpacing(10);

        VBsesije.getChildren().addAll(sesijaLb, TVSesije);
        VBsesije.setSpacing(10);

        container.getChildren().addAll(VBsesije,VBbuttons);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);

        VBistrazivac.getChildren().addAll(DbrLb,istrazivaciComboBox);
        VBistrazivac.setSpacing(10);
        VBistrazivac.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(VBforenzika, container,VBistrazivac);
        this.setSpacing(10);
        this.setPadding(new Insets(20,0,10,10));
        this.setAlignment(Pos.CENTER_LEFT);

    }

    private void loadData(){


    }
    private void createTable(){

        /*
        TableColumn colID= new TableColumn("ID");
        TableColumn colIme= new TableColumn("Ime");
        TableColumn colPrezime= new TableColumn("Prezime");

        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colIme.setCellValueFactory(new PropertyValueFactory("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory("prezime"));


         */

    }
}
