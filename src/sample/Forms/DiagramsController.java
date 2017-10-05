package sample.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Александр on 05.10.2017.
 */
public class DiagramsController implements Initializable {
    @FXML
    PieChart chartDay;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ObservableList<Integer>values = new ObservableList<Integer>() {};
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        answer.addAll(new PieChart.Data("java", 17.56),
                new PieChart.Data("JavaFx", 31.37));
        chartDay.setData(answer);
    }
}
