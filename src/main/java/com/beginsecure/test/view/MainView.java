package com.beginsecure.test.view;

import com.beginsecure.test.controller.DeleteAction;
import com.beginsecure.test.controller.FilterAction;
import com.beginsecure.test.model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class MainView extends HBox {

    private HBox container;
    private VBox VBforenzika, VBsesije, VBbuttons, VBistrazivac, VBizvodjenje;
    private TableView<Forenzicka_Istraga> TVistrage;
    private TableView<Sesija> TVSesije;
    private TableView<Izvodjenje> tvIzvodjenja;
    private ComboBox<Istrazivac> istrazivaciComboBox;
    private Button  obrisiBtn;
    private Label istragaLb, DbrLb, sesijaLb,izvodjenjeLB;
    private CheckBox filterCheckBox;
    private boolean flag;




    private ObservableList<Forenzicka_Istraga> listForenzickaIstraga ;
    private ObservableList<Sesija> listSesije;
    private ObservableList<Istrazivac> listIstrazivaci;
    private ObservableList<Izvodjenje> listIzvodjenje;
    private ObservableList<String> statusSelect;



    public MainView(Stage stage) {

        initElements();
        createTable();
        loadControllers();


    }

    private void initElements(){
        flag=true;

        VBforenzika =new VBox();
        VBsesije = new VBox();
        VBizvodjenje = new VBox();
        VBbuttons =new VBox();
        VBistrazivac=new VBox();
        container=new HBox();

        statusSelect=FXCollections.observableArrayList("planirano", "započeto", "otkazano", "završeno uspešno","završeno neuspešno");

       // statusBtn.setText("Status");
        obrisiBtn=new Button("Obrisi");
        obrisiBtn.setOnAction(e-> new DeleteAction(this));
        obrisiBtn.setDisable(true);

       // obrisiBtn.setText("Obrisi");

        istragaLb= new Label("Forenzicke istrage:");
        DbrLb= new Label("Dobro dosli!");
        sesijaLb= new Label("Sesije:");
        izvodjenjeLB=new Label("Izvodjenja odabrane istrage:");

        tvIzvodjenja= new TableView<>();

        TVistrage = new TableView<>();
        listForenzickaIstraga=FXCollections.observableArrayList(Database.getInstance().getIstrageIzvodjenje().keySet());
        TVistrage.setItems(listForenzickaIstraga);
        TVistrage.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, forenzickaIstraga, t1) -> {
                    if(t1!=null){
                        listIzvodjenje=FXCollections.observableArrayList(Database.getInstance().getIstrageIzvodjenje().get(t1));
                        tvIzvodjenja.setItems(listIzvodjenje);
                    }
                }
        );




        TVSesije = new TableView<>();
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
        //listIstrazivaci = FXCollections.observableArrayList(Database.getInstance().getIstrazivaciList());
        listIstrazivaci=FXCollections.observableArrayList(Database.getInstance().getIstrazivaciSesije().keySet());
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

        VBbuttons.getChildren().addAll(obrisiBtn,filterCheckBox);
        filterCheckBox.setAlignment(Pos.BOTTOM_LEFT);
        VBbuttons.setAlignment(Pos.TOP_LEFT);
        VBbuttons.setSpacing(10);
        VBbuttons.setPadding(new Insets(25,0,0,0));

        VBforenzika.getChildren().addAll(istragaLb, TVistrage);
        VBforenzika.setSpacing(10);

        VBizvodjenje.getChildren().addAll(izvodjenjeLB,tvIzvodjenja);
        VBizvodjenje.setSpacing(10);

        VBsesije.getChildren().addAll(sesijaLb, TVSesije);
        VBsesije.setSpacing(10);

        container.getChildren().addAll(VBsesije,VBbuttons);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);

        VBistrazivac.getChildren().addAll(DbrLb,istrazivaciComboBox);
        VBistrazivac.setSpacing(10);
        VBistrazivac.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(VBforenzika,VBizvodjenje, container,VBistrazivac);
        this.setSpacing(10);
        this.setPadding(new Insets(20,0,10,10));
        this.setAlignment(Pos.CENTER_LEFT);

    }

    private void loadControllers(){
        new DeleteAction(this);
        new FilterAction(this);

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


        TableColumn<Forenzicka_Istraga, Integer> colID=new TableColumn<>("ID ISTRAGE");
        TableColumn<Forenzicka_Istraga,String > colName=new TableColumn<>("NAZIV ISTRAGE");

        colID.setCellValueFactory(data->
                new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colName.setCellValueFactory(data->
                new SimpleStringProperty(data.getValue().getNaziv()));
        TVistrage.getColumns().addAll(colID,colName);

        TableColumn<Izvodjenje,Integer> colId=new TableColumn<>("IZVODJENJE");
        TableColumn<Izvodjenje,Integer> colIdI=new TableColumn<>("ISTRAGA");
        TableColumn<Izvodjenje,Integer> colIdL=new TableColumn<>("LAB");
        TableColumn<Izvodjenje,String> coldatum=new TableColumn<>("DATUM");
        TableColumn<Izvodjenje,String> colstatus=new TableColumn<>("STATUS");

        colId.setCellValueFactory(data->
                new SimpleIntegerProperty(data.getValue().getId_izvodjenja()).asObject());

        colIdI.setCellValueFactory(data->
                new SimpleIntegerProperty(data.getValue().getId_forenzicke_istrage()).asObject());

        colIdL.setCellValueFactory(data->
                new SimpleIntegerProperty(data.getValue().getId_laboratorije()).asObject());

        coldatum.setCellValueFactory(data->
                    new SimpleStringProperty(data.getValue().getDatum()));

        colstatus.setCellValueFactory(data->(
                new SimpleStringProperty(data.getValue().getStatus())));
        colstatus.setEditable(true);
        colstatus.setCellFactory(ComboBoxTableCell.forTableColumn(statusSelect));
        colstatus.setOnEditCommit(e->{
            Izvodjenje izvodjenje = e.getRowValue();
            String newvalue=e.getNewValue();
            izvodjenje.setStatus(newvalue);

            ///  DODAJ DATABSE UPDTE
        });




        tvIzvodjenja.getColumns().addAll(colstatus,coldatum,colId,colIdI,colIdL);
        tvIzvodjenja.setEditable(true);




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

    public ObservableList<Sesija> getListSesije() {
        return listSesije;
    }

    public ObservableList<Istrazivac> getListIstrazivaci() {
        return listIstrazivaci;
    }

    public ObservableList<Forenzicka_Istraga> getListForenzickaIstraga() {
        return listForenzickaIstraga;
    }

    public CheckBox getFilterCheckBox() {
        return filterCheckBox;
    }

    public TableView<Forenzicka_Istraga> getTVistrage() {
        return TVistrage;
    }

    public TableView<Izvodjenje> getTvIzvodjenja() {
        return tvIzvodjenja;
    }
}
