package sample.Forms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.FileIO.FileIO;
import sample.Models.TType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 30.09.2017.
 */
public class AddTypeController {
    @FXML
    Button addBtn;
    @FXML
    private javafx.scene.control.TextField txbValue;
    Common common = new Common();
    FileIO fileIOTypes = new FileIO("types.txt");

    public void btnAdd_click() throws Exception {
        try {
            List<TType> types = fileIOTypes.OpenTypes();
            if (txbValue.getText().length() > 0) {
                if (types.size() == 0)
                    types.add(new TType(txbValue.getText()));
                else
                    types.add(new TType(types.get(types.size() - 1).GetId() + 1, txbValue.getText()));
                fileIOTypes.SaveTypes(types);
                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            } else {
                throw new Exception("Название не может быть пустой строкой!");
            }
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }
    }
}
