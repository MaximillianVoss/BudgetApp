package sample.Forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {
    @FXML private Button btnIncome;
    @FXML private Button btnOutcome;
    @FXML private Button btnAccounts;

    public void initialize() throws IOException {}


    public  void ShowForm(String name) throws IOException{
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/sample/Forms/"+name));
        //FXMLLoader loader  = new FXMLLoader(getClass().getResource("Forms/"));
        Parent root = (Parent)loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void  btnIncome_click() throws IOException {
        try {
            ShowForm("IncomeForm.fxml");
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    public void btnAccounts_click()throws IOException{
        try {
            ShowForm("AccountsForm.fxml");
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    public void btnOutCome_click()throws IOException{
        try {
            ShowForm("OutcomeForm.fxml");
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

}
