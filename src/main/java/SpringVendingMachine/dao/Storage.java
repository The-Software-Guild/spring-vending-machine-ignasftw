package SpringVendingMachine.dao;

import java.util.Map;

public interface Storage {
    void addItem(Data item);
    void updateItem(int id, Data item);
    void removeItem(int id);
    Map<Integer, Data> getItems();
    Data getItem(int id);
    int getLastId();
}
