package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Forms/MainForm.fxml"));
        primaryStage.setTitle("BudgetApp");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();



    }

//    List<Item> income,outcome;
//    List<TType> types;
//    String names []=new String[]{"food","clothes","gaz","car"};
//    void Init(){
//        for(int i=0;i<names.length;i++){
//            types.add(new TType(names[i]));
//        }
//        for(int i=0;i<names.length;i++){
//            income.add(new Item(i,"income"+i,999,new Date("1.1.2017"),types.get(i)));
//        }
//
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
