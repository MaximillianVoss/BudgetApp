package sample.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Models.Item;
import sample.Models.TType;

import java.io.IOException;
import java.util.List;

/**
 * Created by Александр on 30.09.2017.
 */
public class Common {
    public void ShowForm(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Forms/" + name));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void FillCmb(ComboBox cmb, List<TType> items) {
        ObservableList<TType> obsItems = FXCollections.observableArrayList();
        for (TType item : items)
            obsItems.add(item);
        obsItems.add(new TType(items.size(),"Добавить категорию"));
        cmb.setItems(obsItems);
    }

    public void FillLst(ListView lst, List<Item> items) {
        ObservableList<Item> obsItems = FXCollections.observableArrayList();
        for (Item item : items)
            obsItems.add(item);
        lst.setItems(obsItems);
    }

    public void ShowMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}