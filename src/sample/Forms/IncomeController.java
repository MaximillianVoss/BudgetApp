package sample.Forms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.FileIO.FileIO;
import sample.FormsFill.Filler;
import sample.Main;
import sample.Models.Item;
import sample.Models.TType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 17.09.17.
 */
public class IncomeController extends Stage {
    @FXML
    private AnchorPane root;
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
    Filler filler = new Filler();
    FileIO fileIO = new FileIO("chache.txt");
    private List<TType> CreateTypes(){
        List<TType> types = new ArrayList<TType>();
        String names []  = {"Зарплата","Бизнес","Дивиденды","Подарки","Подработка"};
        for(int i=0;i< names.length;i++)
            types.add(new TType(i,names[i]));
        return types;
    }

    public void cmb_OnShow(){
        try {
            types = CreateTypes();
            filler.FillCmb(cmbTypes, types);
        }
        catch (Exception ex){
            filler.ShowMessage(ex.getMessage());
        }
    }

    public void form_onShow(){
        try {
            filler.FillLst(lstItems, fileIO.Open());
        }
        catch (Exception ex){
            filler.ShowMessage(ex.getMessage());
        }
    }

    public  void btnAdd_click(){
        try{
            double val = Double.parseDouble(txbValue.getText());
            TType type= (TType)cmbTypes.getValue();
            if(type==null)
                throw new Exception("Выберите категорию");
            Date date = new Date();
            incomes.add(new Item(incomes.size()+1,val,date,type));
            filler.FillLst(lstItems,incomes);
            fileIO.Save(incomes);
        }
        catch (Exception ex){
            filler.ShowMessage(ex.getMessage());
        }

    }
}