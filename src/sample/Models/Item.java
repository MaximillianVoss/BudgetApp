package sample.Models;

import java.util.Date;

/**
 * Created by mark on 17.09.17.
 */

//enum Type {grocery,food,clothes,gaz,car,home,holiday,mobile,internet,other}

public class Item {
    int id;
    String name;
    double value;
    Date date;
    TType type;

    public Item(){

    }
    public Item(int _id, String _name,double _value, Date _date, TType _type){
        id=_id;
        name=_name;
        value=_value;
        date=_date;
        type=_type;
    }

    @Override
    public String toString(){
       return "NA";
    }
}
