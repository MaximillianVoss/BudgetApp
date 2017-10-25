package sample.Forms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.FileIO.FileIO;
import sample.Models.Item;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Александр on 05.10.2017.
 */
public class ChartsController implements Initializable {
    @FXML
    PieChart chartDay;
    @FXML
    PieChart chartMonth;
    @FXML
    PieChart chartYear;
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox chbMonth;
    @FXML
    ChoiceBox chbYear;
    @FXML
    ChoiceBox chbYear2;

    private int type = -1;
    private ArrayList<Item> items = new ArrayList<Item>();
    private FileIO io = new FileIO("cache.txt");

    public void SetItems(ArrayList<Item> _items) {
        this.items = _items;
    }

    public void SetType(int _type) {
        this.type = _type;
    }

    public void datePicker_clicked() {
        FillChart();
    }

    public void chbMonth_clicked() {
        FillChart();
    }

    public void chbYear_clicked() {
        FillChart();
    }

    public void chbYear2_clicked() {
        FillChart();
    }

    public LocalDate ConvertToLDate(Date date) {
        LocalDate res = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return res;
    }

    public ObservableList<PieChart.Data> ConvertToChartData(Map<String, Item> dict) {
        ObservableList<PieChart.Data> res = FXCollections.observableArrayList();
        for (String key : dict.keySet())
            res.addAll(new PieChart.Data(dict.get(key).type.GetName(), dict.get(key).value));
        return res;
    }


    public void FillChart() {
        ObservableList<PieChart.Data> answerDay = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> answerMonth = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> answerYear = FXCollections.observableArrayList();

        answerDay.removeAll(answerDay);
        answerMonth.removeAll(answerMonth);
        answerYear.removeAll(answerYear);

        Map<String, Item> dict = new HashMap<String, Item>();
        for (Item item : items) {
            if (datePicker.getValue() != null && datePicker.getValue().equals(ConvertToLDate(item.date))) {
                if (dict.get(item.type.GetName()) == null)
                    dict.put(item.type.GetName(), item);
                else
                    dict.get(item.type.GetName()).value += item.value;
            }
        }
        chartDay.setData(ConvertToChartData(dict));

        dict = new HashMap<String, Item>();
        for (Item item : items) {
            int month = Integer.valueOf((String) chbMonth.getValue());
            int year = Integer.valueOf((String) chbYear.getValue());
            if (ConvertToLDate(item.date).getMonthValue() == month && ConvertToLDate(item.date).getYear() == year) {
                if (dict.get(item.type.GetName()) == null)
                    dict.put(item.type.GetName(), item);
                else
                    dict.get(item.type.GetName()).value += item.value;
            }
        }
        chartMonth.setData(ConvertToChartData(dict));

        dict = new HashMap<String, Item>();
        for (Item item : items) {
            int year = Integer.valueOf((String) chbYear2.getValue());
            if (ConvertToLDate(item.date).getYear() == year) {
                if (dict.get(item.type.GetName()) == null)
                    dict.put(item.type.GetName(), item);
                else
                    dict.get(item.type.GetName()).value += item.value;
            }
        }
        chartYear.setData(ConvertToChartData(dict));
    }

    public void FillChoiseBoxes() {
        ObservableList<String>
                months = FXCollections.observableArrayList(),
                years = FXCollections.observableArrayList();
        for (int i = 0; i < 12; i++)
            months.add(Integer.toString(i + 1));
        for (int i = 2017; i < 2020; i++)
            years.add(Integer.toString(i));
        chbMonth.setItems(months);
        chbMonth.setValue(months.get(0));
        chbYear.setItems(years);
        chbYear.setValue(years.get(0));
        chbYear2.setItems(years);
        chbYear2.setValue(years.get(0));

        chbMonth.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                int index = chbMonth.getSelectionModel().getSelectedIndex();
                chbMonth.getSelectionModel().select(index);
                FillChart();
            }
        });

        chbYear.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                int index = chbYear.getSelectionModel().getSelectedIndex();
                chbYear.getSelectionModel().select(index);
                FillChart();
            }
        });

        chbYear2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                int index = chbYear2.getSelectionModel().getSelectedIndex();
                chbYear2.getSelectionModel().select(index);
                FillChart();
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ObservableList<Item> list = new ObservableList<Item>() {};
        //list.add(0,1);
        //chartDay.setData(new Data());
        //datePicker.setOnAction(new EventHandler<ActionEvent>(){
        //@Override
        //public void handle(ActionEvent event) {
        // }
        //});
        try {
            if (io.GetTemp().equals("income")) {
                items = io.incomes;
            } else if (io.GetTemp().equals("outcome")) {
                items = io.outcomes;
            }
            datePicker.setValue(ConvertToLDate(new Date()));
            FillChoiseBoxes();
            FillChart();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
