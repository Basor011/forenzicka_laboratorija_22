package com.beginsecure.test.view;

import com.beginsecure.test.model.Forenzicka_Istraga;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainView extends BorderPane {

    private TableView<Forenzicka_Istraga> ISTRAGETV;
    private TableView<Sesija> SESIJELTV;
    private ComboBox<Istrazivac> istrazivaciComboBox;
    private Button statusBtn, obrisiBtn;
    private Label istragaLb, DbrLb, filterLb;
    private CheckBox filterCheckBox;

    private ObservableList<Forenzicka_Istraga> ISTRAGE ;
    private ObservableList<Sesija> SESIJE;
    private ObservableList<Istrazivac> ISTRAZIVACI;


    public MainView(Stage stage) {

        initElements();
        loadData();



    }

    private void initElements(){

        statusBtn= new Button();
        statusBtn.setText("Status");
        obrisiBtn=new Button();
        obrisiBtn.setText("Obrisi");

        istragaLb= new Label("Forenzicke istrage:");
        DbrLb= new Label("Dobro dosli!");
        filterLb= new Label("Prikazi sopstvene sesije");

        ISTRAGETV= new TableView<Forenzicka_Istraga>();
        SESIJELTV = new TableView<Sesija>();

        istrazivaciComboBox= new ComboBox<Istrazivac>();

      /// ISTRAGE= FXCollections.observableArrayList();


    }

    private void loadData(){


    }
    private void createTable(){


    }
}
