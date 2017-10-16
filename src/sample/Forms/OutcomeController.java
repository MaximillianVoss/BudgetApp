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
public class OutcomeController implements Initializable {

    //<editor-fold desc="Элементы управления">
    @FXML
    private TextField txbValue;
    @FXML
    private ListView lstItems;
    @FXML
    private ComboBox cmbTypes;
    @FXML
    private Button btnTypeDelete;
    //</editor-fold>

    //<editor-fold desc="Поля">
    private FileIO fileIO = new FileIO("chache.txt");
    private Common common = new Common();
    //</editor-fold>

    //<editor-fold desc="Методы">
    public void Load() {
        try{
            fileIO.Init();
            LoadItems();
        }catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void AddValue() {
        try {
            double val = Double.parseDouble(txbValue.getText());
            TType type = (TType) cmbTypes.getValue();
            if (type == null || type.GetId() == fileIO.outomeTypes.size())
                throw new Exception("Выберите категорию");
            Date date = new Date();
            fileIO.outcomes.add(new Item(fileIO.outcomes.size() + 1, val, date, type));
            LoadItems();
            fileIO.SaveAll();
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void DeleteValue() {
        try {
            Item item = (Item) lstItems.getSelectionModel().getSelectedItem();
            fileIO.outcomes.remove(item);
            common.FillLst(lstItems, fileIO.outcomes);
            fileIO.SaveAll();
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void LoadItems() {
        try {
            common.FillLst(lstItems, fileIO.outcomes);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void DeleteType() {
        TType type = (TType) cmbTypes.getValue();
        try {
            fileIO.outomeTypes.remove(type);
            fileIO.SaveAll();
            cmbTypes.setValue(null);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }

    private void LoadTypes() {
        try {
            //fileIO.OpenTypes();
            common.FillCmb(cmbTypes, fileIO.types.get("outcomeTypes"));
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold desc="События">
    public void cmb_OnShow() throws Exception {
        LoadTypes();
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
    //</editor-fold>

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Load();
        cmbTypes.valueProperty().addListener(new ChangeListener<TType>() {
            @Override
            public void changed(ObservableValue ov, TType t, TType t1) {
                TType type = (TType) cmbTypes.getValue();
                if (type.GetId() == fileIO.outomeTypes.size()) {
                    try {
                        common.ShowAddTypeForm("AddTypeForm.fxml",2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}