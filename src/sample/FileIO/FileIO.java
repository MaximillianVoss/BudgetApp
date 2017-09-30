package sample.FileIO;

import sample.Models.Item;
import sample.Models.TType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 24.09.2017.
 */
public class FileIO {
    //TODO: довисать конструктор для Ttype
    //доделать open/save для него
    //додлеать форму для добавления типа
    private String fileName;

    public FileIO() {
        fileName = "";
    }

    public FileIO(String _fileName) {
        fileName = _fileName;
    }

    public List<Item> Open(String _fileName) throws Exception {
        List<Item> items = new ArrayList<Item>();
        FileReader reader = new FileReader(_fileName);
        BufferedReader bufferReader = new BufferedReader(reader);
        String line = "";
        while ((line = bufferReader.readLine()) != null) {
            //"%d|%s|%f|%s|%s \n",id,name,value,date,type
            String values[] = line.split(";");
            Item temp = new Item(values);
            items.add(new Item(values));
        }
        return items;
    }

    public List<TType> OpenTypes(String _fileName) throws Exception {
        List<TType> items = new ArrayList<TType>();
        FileReader reader = new FileReader(_fileName);
        BufferedReader bufferReader = new BufferedReader(reader);
        String line = "";
        while ((line = bufferReader.readLine()) != null) {
            //"%d|%s|%f|%s|%s \n",id,name,value,date,type
            String values[] = line.split(";");
            Item temp = new Item(values);
            items.add(new TType(values));
        }
        return items;
    }

    public  List<TType>OpenTypes()throws  Exception{
        return OpenTypes(this.fileName);
    }

    public List<Item> Open() throws Exception {
        return Open(fileName);
    }

    public void Save(String _fileName, List<Item> items) throws Exception {

        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
        for (Item item : items)
            bufferWriter.write(item.toStr());
        bufferWriter.close();
    }

    public void Save(List<Item> items) throws Exception {
        Save(fileName, items);
    }

    public void SaveTypes(String _fileName, List<TType> items) throws Exception {
        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
        for (TType item : items)
            bufferWriter.write(item.toStr());
        bufferWriter.close();
    }

    public void SaveTypes(List<TType> items) throws Exception {
        SaveTypes(this.fileName, items);
    }
}
