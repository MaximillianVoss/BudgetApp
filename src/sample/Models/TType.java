package sample.Models;

/**
 * Created by mark on 17.09.17.
 */
public class TType {

    int id;
    String name;
    public TType(){

    }
    public TType(int _id,String _name){
        id=_id;
        name=_name;
    }
    public TType(String _name){
        name=_name;
    }

    public String GetName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }


}