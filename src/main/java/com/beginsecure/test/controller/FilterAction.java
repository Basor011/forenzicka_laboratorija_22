package com.beginsecure.test.controller;

import com.beginsecure.test.model.Database;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import com.beginsecure.test.view.MainView;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class FilterAction {
    private MainView view;
    private Database instance;

    public FilterAction(MainView view) {
        this.view = view;
        instance=Database.getInstance();

        this.view.getFilterCheckBox().setOnAction(e-> filterAction());
    }
    private void filterAction(){
        Istrazivac istrazivac=view.getIstrazivaciComboBox().getSelectionModel().getSelectedItem();
        if(view.getFilterCheckBox().isSelected() && istrazivac!=null){
            ArrayList<Sesija> sesije = instance.getFilterList(istrazivac);
           // System.out.println(sesije + " <-- FILTER ACTION");
            if(sesije==null){
                view.getTVSesije().setItems(FXCollections.observableArrayList(new ArrayList<>()));
                return;

            }

           view.getTVSesije().setItems(FXCollections.observableArrayList(sesije));

        } else {
            view.getTVSesije().setItems(view.getListSesije());
        }

    }
}
