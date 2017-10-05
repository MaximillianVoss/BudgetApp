package sample.Forms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.FileIO.FileIO;
import sample.Models.Item;
import sample.Models.TType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mark on 17.09.17.
 */
public class IncomeController implements Initializable {
    @FXML
    private TextField txbValue;
    @FXML
    private ListView lstItems;
    @FXML
    private ComboBox cmbTypes;
    @FXML
    private Button btnTypeDelete;

    List<Item> incomes = new ArrayList<Item>();
    List<TType> types = new ArrayList<TType>();
    FileIO fileIOItems = new FileIO("chache.txt");
    FileIO fileIOTypes = new FileIO("types.txt");
    Common common = new Common();

    private List<TType> CreateTypes() {
        List<TType> types = new ArrayList<TType>();
        String names[] = {"Зарплата", "Бизнес", "Дивиденды", "Подарки", "Подработка"};
        for (int i = 0; i < names.length; i++)
            types.add(new TType(i, names[i]));
        types.add(new TType(names.length + 1, "Добавить категорию"));
        return types;
    }

    private void AddValue() {
        try {
            double val = Double.parseDouble(txbValue.getText());
            TType type = (TType) cmbTypes.getValue();
            if (type == null)
                throw new Exception("Выберите категорию");
            Date date = new Date();
            incomes.add(new Item(incomes.size() + 1, val, date, type));
            common.FillLst(lstItems, incomes);
            fileIOItems.Save(incomes);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void DeleteValue() {
        try {
            Item item = (Item) lstItems.getSelectionModel().getSelectedItem();
            incomes.remove(item);
            common.FillLst(lstItems, incomes);
            fileIOItems.Save(incomes);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void LoadItems() {
        try {
            incomes = fileIOItems.Open();
            common.FillLst(lstItems, incomes);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void AddType() {

    }

    private void DeleteType() {
        TType type = (TType) cmbTypes.getValue();
        //for(int i=0;i<types.size();i++)
        //{
        //    if(types.get(i).GetId()==type.GetId())

        //}
        try {
            types.remove(type);
            fileIOTypes.SaveTypes(types);
            cmbTypes.setValue(null);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void LoadTypes() {
        try {
            types = fileIOTypes.OpenTypes();
            common.FillCmb(cmbTypes, types);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    public void cmb_OnShow() throws Exception {
        types = fileIOTypes.OpenTypes();
        common.FillCmb(cmbTypes, types);
    }

    public void btnAdd_click() {
        AddValue();
    }

    public void btnDelete_click() {
        DeleteValue();
    }

    public void btnTypeDelete_click() {
        DeleteType();
    }

    public  void btnDiagrams_click()throws Exception{
            common.ShowForm("ChartsForm.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadItems();
        LoadTypes();
        cmbTypes.valueProperty().addListener(new ChangeListener<TType>() {
            @Override
            public void changed(ObservableValue ov, TType t, TType t1) {
                TType type = (TType) cmbTypes.getValue();
                if (type.GetId() == types.size()) {
                    //common.ShowMessage(type.toString());
                    try {
                        common.ShowForm("AddTypeForm.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}