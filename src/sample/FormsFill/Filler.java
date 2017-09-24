package sample.FormsFill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import sample.Models.Item;
import sample.Models.TType;

import java.util.List;

/**
 * Created by Александр on 24.09.2017.
 */
public class Filler {
    public Filler(){

    }
    public void FillCmb(ComboBox cmb, List<TType> items) {
        ObservableList<TType> obsItems = FXCollections.observableArrayList();
        for (TType item : items)
            obsItems.add(item);
        cmb.setItems(obsItems);
    }
    public void FillLst(ListView lst, List<Item> items){
        ObservableList<Item> obsItems = FXCollections.observableArrayList();
        for (Item item : items)
            obsItems.add(item);
        lst.setItems(obsItems);
    }
    public void ShowMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
