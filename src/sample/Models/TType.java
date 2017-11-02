package sample.Models;

/**
 * Created by mark on 17.09.17.
 */
public class TType {

    int id;
    String name;
    boolean standart;

    public TType() {

    }

    /**
     * создает тип с указанными свойствами
     * @param _id ИД
     * @param _name имя типа
     */
    public TType(int _id, String _name) {
        id = _id;
        name = _name;
    }

    public TType(String _id, String _name) {
        id = Integer.parseInt(_id);
        name = _name;
    }

    public TType(String values[]) {
        try {
//            if (values.length < 2)
//                throw new Exception("Wrong values count");
            id = Integer.parseInt(values[0]);
            name = values[1];
        } catch (Exception ex) {
            new Item();
        }
    }

    public TType(String _name) {
        name = _name;
    }

    public String GetName() {
        return name;
    }

    public int GetId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toStr() {
        return String.format("%d;%s\n", this.id, this.name);
    }


}