package SpringVendingMachine.controller;
import SpringVendingMachine.dao.Data;
import SpringVendingMachine.dao.Storage;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileModifier {
    private List<Data> data = new ArrayList<>();
    private final String path = System.getProperty("user.dir")+"/src/main/java/SpringVendingMachine/dao/";
    private final File myObj = new File(path+"ItemStorage.txt");

    public void readFile() throws FileNotFoundException {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String row = myReader.nextLine();
                String[] tokens=row.split("::");
                Data item = new Data();
                item.setItem(tokens[0], new BigDecimal(tokens[1]), Integer.parseInt(tokens[2]));
                data.add(item);
            }
            myReader.close();
    }

    public void writeFile(Storage data){
        try {
            PrintWriter out = new PrintWriter(new FileWriter(path+"ItemStorage.txt"));
            for(Data row : data.getItems().values()){
                out.println(String.format(
                        "%s::%s::%s",
                        row.getName(),
                        row.getPrice(),
                        row.getAmount()
                ));
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Data> getData(){
        return data;
    }
}
