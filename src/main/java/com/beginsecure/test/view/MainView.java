package com.beginsecure.test.view;

import com.beginsecure.test.controller.DeleteAction;
import com.beginsecure.test.model.Database;
import com.beginsecure.test.model.Forenzicka_Istraga;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private boolean flag;




    private ObservableList<Forenzicka_Istraga> listForenzickaIstraga ;
    private ObservableList<Sesija> listSesije;
    private ObservableList<Istrazivac> listIstrazivaci;


    public MainView(Stage stage) {

        initElements();
        createTable();
        loadControllers();


    }

    private void initElements(){
        flag=true;

        VBforenzika =new VBox();
        VBsesije = new VBox();
        VBbuttonsComp = new VBox();
        VBbuttons =new VBox();
        VBistrazivac=new VBox();
        container=new HBox();

        statusBtn= new Button("Status");

       // statusBtn.setText("Status");
        obrisiBtn=new Button("Obrisi");
        obrisiBtn.setOnAction(e-> new DeleteAction(this));
        obrisiBtn.setDisable(true);

       // obrisiBtn.setText("Obrisi");

        istragaLb= new Label("Forenzicke istrage:");
        DbrLb= new Label("Dobro dosli!");
        sesijaLb= new Label("Sesije:");

        TVistrage = new TableView<Forenzicka_Istraga>();
        TVSesije = new TableView<Sesija>();
        listSesije=FXCollections.observableArrayList(Database.getInstance().getSesijeList());
        TVSesije.setItems(listSesije);
        TVSesije.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, sesija, t1) -> {
                    if(t1!=null ){
                        if(!flag){
                            obrisiBtn.setDisable(false);
                        }
                        else flag=false;


                    }
                }
        );



        istrazivaciComboBox= new ComboBox<>();
        listIstrazivaci = FXCollections.observableArrayList(Database.getInstance().getIstrazivaciList());
        istrazivaciComboBox.setItems(listIstrazivaci);
        istrazivaciComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, stari, novi) -> {
                    if (novi != null) {
                        if (!flag) {
                            obrisiBtn.setDisable(false);
                        }else flag=false;

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

    private void loadControllers(){
        new DeleteAction(this);



    }
    private void createTable(){

        TableColumn<Sesija, Integer> colIzvodjenje = new TableColumn("IZVODJENJE");
        TableColumn<Sesija, String> colPocetak = new TableColumn("POCETAK");
        TableColumn<Sesija, String> colKraj = new TableColumn("KRAJ");
        TableColumn<Sesija, String> colDatum = new TableColumn("DATUM");
        TableColumn<Sesija, Integer> colSesija = new TableColumn("SESIJA");


        colIzvodjenje.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId_izvodjenja()).asObject());
        colPocetak.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPocetak()));
        colKraj.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getKraj()));
        colDatum.setCellValueFactory(data->
                new SimpleStringProperty(data.getValue().getDatum()));
        colSesija.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId()).asObject());

        TVSesije.getColumns().addAll(colIzvodjenje,colPocetak,colKraj,colDatum,colSesija);





    }


    public Button getObrisiBtn() {
        return obrisiBtn;
    }

    public ComboBox<Istrazivac> getIstrazivaciComboBox() {
        return istrazivaciComboBox;
    }

    public TableView<Sesija> getTVSesije() {
        return TVSesije;
    }
}
