package sample.Forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.Models.Item;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Александр on 05.10.2017.
 */
public class ChartsController implements Initializable {
    @FXML
    PieChart chartDay;
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox chbDay;
    @FXML
    ChoiceBox chbYear;

    private int type = -1;
    private ArrayList<Item> items = new ArrayList<Item>();

    public void SetItems(ArrayList<Item> _items) {
        this.items = _items;
    }

    public void SetType(int _type) {
        this.type = _type;
    }

    public void datePicker_clicked() {
        FillChart();
    }

    public LocalDate ConvertToLDate(Date date) {
        Date input = new Date();
        LocalDate res = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return res;
    }

    public void FillChart() {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        for (Item item : items) {
            if (datePicker.getValue() != null && datePicker.getValue().equals(ConvertToLDate(item.date)))
                answer.addAll(new PieChart.Data(item.type.GetName(), item.value));
        }
        chartDay.setData(answer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ObservableList<Item> list = new ObservableList<Item>() {};
        //list.add(0,1);
        //chartDay.setData(new Data());
        datePicker.setValue(ConvertToLDate(new Date()));
        FillChart();
    }
}
