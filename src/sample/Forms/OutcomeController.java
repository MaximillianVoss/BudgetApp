package sample.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Models.Item;
import sample.Models.TType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 17.09.17.
 */
public class OutcomeController {
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txbValue;
    @FXML
    private ListView lstItems;
    @FXML
    private ComboBox cmbTypes;

    List<Item> incomes = new ArrayList<Item>();
    List<TType> types = new ArrayList<TType>();

    private List<TType> CreateTypes(){
        List<TType> types = new ArrayList<TType>();
        String names []  = {"Продукты","Обеды","Одежда","Спорт","Машина","Дом","Отдых","Мобильный","Интернет"};
        for(int i=0;i< names.length;i++)
            types.add(new TType(i,names[i]));
        return types;
    }
    private void FillCmb(ComboBox cmb,List<TType> items) {
        ObservableList<TType> obsItems = FXCollections.observableArrayList();
        for (TType item : items)
            obsItems.add(item);
        cmb.setItems(obsItems);
    }

    public void cmb_OnShow(){
        types = CreateTypes();
        FillCmb(cmbTypes,types);
    }
    private void ShowMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public  void btnAdd_click(){
        try{
            double val = Double.parseDouble(txbValue.getText());
            TType type= (TType)cmbTypes.getValue();
            Date date = new Date();
            incomes.add(new Item(0,"no name",val,date,type));

        }
        catch (Exception ex){
            ShowMessage(ex.getMessage());
        }

    }



}