package sample.Forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.FileIO.FileIO;
import sample.Models.Item;
import sample.Models.TType;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by mark on 17.09.17.
 */

public class AccountsController implements Initializable {
    //<editor-fold desc="Controls">
    @FXML
    Button btnAdd;
    @FXML
    Button btnDelete;
    @FXML
    Button btnSubVal;
    @FXML
    Button btnAddVal;
    @FXML
    TextField txbName;
    @FXML
    TextField txbValue;
    @FXML
    TextField txbIncome;
    @FXML
    TextField txbOutcome;
    @FXML
    ListView lstAcc;
    //</editor-fold>

    //TODO: доделать работу со счетами
    //TODO:  повесить функции на кнопки
    //<editor-fold desc="Fields">
    private Common common = new Common();
    private FileIO io = new FileIO(common.GetFileName());
    //</editor-fold>

    //<editor-fold desc="Методы">
    private void Open() {
        try {
            io.OpenAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FillList() {
        Open();
        common.FillLst(lstAcc, io.accounts);
    }

    public void Add() {
        if (txbName.getText().length() > 0 && txbValue.getText().length() > 0) {
            double val = Double.parseDouble(txbValue.getText());
            io.accounts.add(new Item(io.accounts.size() + 1, txbName.getText(), val, new Date(), new TType(common.bankAccountTypeId, common.bankAccountTypeStr)));
            try {
                io.SaveAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FillList();
        } else
            common.ShowMessage(common.notFilledStr);
    }

    public void Delete() {
        Item item = (Item) lstAcc.getSelectionModel().getSelectedItem();
        if (item != null) {
            io.accounts.remove(item);
            try {
                io.SaveAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FillList();
        }
    }

    public void AddValue() {
        Item item = (Item) lstAcc.getSelectionModel().getSelectedItem();
        if (item != null) {
            double val = Double.parseDouble(txbIncome.getText());
            item.value += val;
        }
        try {
            io.SaveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FillList();
    }

    public void SubValue() {
        Item item = (Item) lstAcc.getSelectionModel().getSelectedItem();
        if (item != null) {
            double val = Double.parseDouble(txbOutcome.getText());
            item.value -= val;
        }
        try {
            io.SaveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FillList();
    }
    //</editor-fold>


    public void btnAdd_click() {
        Add();
    }

    public void btnSubVal_click() {
        SubValue();
    }

    public void btnAddVal_click() {
        AddValue();
    }

    public void btnDelete_click() {
        Delete();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FillList();
    }
}