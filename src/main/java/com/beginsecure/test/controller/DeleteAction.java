package com.beginsecure.test.controller;


import com.beginsecure.test.model.Database;
import com.beginsecure.test.model.Istrazivac;
import com.beginsecure.test.model.Sesija;
import com.beginsecure.test.view.MainView;
import javafx.scene.control.Alert;

public class DeleteAction {

   private  MainView view;
   private Database instance;

    public DeleteAction(MainView view) {
        this.view = view;
        instance=Database.getInstance();

        this.view.getObrisiBtn().setOnAction(e-> deleteAction());
    }

    private void deleteAction(){
        //selektovana sesija je ?
       Istrazivac istrazivac= view.getIstrazivaciComboBox().getSelectionModel().getSelectedItem();
       Sesija sesija = view.getTVSesije().getSelectionModel().getSelectedItem();
       if(sesija == null || istrazivac== null) return;
       boolean legal=instance.checkLegal(sesija,istrazivac);
       if(legal) instance.deleteEntry(sesija);
       else{
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Greska");
           alert.setHeaderText("Brisanje neuspesno");
           alert.setContentText("Izabrani istrazivac ne prisustvuje ovoj sesiji!");
           alert.showAndWait();

       }




    }
}
