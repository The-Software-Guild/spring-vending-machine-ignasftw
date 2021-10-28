package SpringVendingMachine.dao;

import java.util.HashMap;
import java.util.Map;

public class DataStorage implements Storage {
    private int i = 100;
    private Map<Integer, Data> itemMap = new HashMap<>();

    public void addItem(Data item) {
        itemMap.put(i++, item);
    }

    public void updateItem(int id, Data item) {
        itemMap.replace(id, item);
    }

    public void removeItem(int id) {
        itemMap.remove(id);
    }

    public Map<Integer, Data> getItems() {
        return itemMap;
    }

    public Data getItem(int id) {
        return itemMap.get(id);
    }

    public int getLastId() {
        return i;
    }
}

