package sample.Forms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.FileIO.FileIO;
import sample.Models.TType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 30.09.2017.
 */
public class AddTypeController {
    @FXML
    private javafx.scene.control.TextField txbValue;

    FileIO fileIOTypes = new FileIO("types.txt");
    public void btnAdd_click() throws Exception {
        List<TType> temp = fileIOTypes.OpenTypes();
        temp.add(new TType(txbValue.getText()));
        fileIOTypes.SaveTypes(temp);
    }
}
