package SpringVendingMachine.servicelayer;

import SpringVendingMachine.controller.Control;
import SpringVendingMachine.dao.Data;
import SpringVendingMachine.dao.DataStorage;
import SpringVendingMachine.dao.Storage;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTesting {
    TestInterface userInterface;
    Storage storage;
    Control control;
    List<Integer> inputs = new ArrayList<>();

    public VendingMachineTesting() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        userInterface = ctx.getBean("userInterface", TestInterface.class);
        storage = ctx.getBean("dataStorage", DataStorage.class);
        control = ctx.getBean("controller", Control.class);
    }

    @Test
    public void insufficientFundsExceptionThrows() {
        inputs.clear();
        inputs.add(2);
        inputs.add(102);
        inputs.add(4);
        userInterface.setInputs(inputs);
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> control.start());
        Assert.assertEquals("Sorry, insufficient funds. Current amount: [0.00] item price [1.99]", exception.getMessage());
    }

    @Test
    public void noItemInventoryExceptionThrows() {
        inputs.clear();
        inputs.add(1);
        inputs.add(10);
        inputs.add(2);
        inputs.add(101);
        inputs.add(4);
        userInterface.setInputs(inputs);
        NoItemInventoryException exception = assertThrows(NoItemInventoryException.class, () -> control.start());
        Assert.assertEquals("The item [101] ItemNr1 is out of stock.", exception.getMessage());
    }

    @Test
    public void firstItemImported() {
        inputs.clear();
        inputs.add(4);
        userInterface.setInputs(inputs);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(100);
        Boolean test = item.getName().equals("ItemNr1") &&
                item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                item.getAmount() == 0;
        ;
        Assert.assertTrue(test);
    }

    @Test
    public void firstItemNotEqualSecond() {
        inputs.clear();
        inputs.add(4);
        userInterface.setInputs(inputs);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(101);
        Boolean test = item.getName().equals("ItemNr1") &&
                item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                item.getAmount() == 0;
        ;
        Assert.assertFalse(test);
    }

    @Test
    public void updateItemImported() {
        inputs.clear();
        inputs.add(4);
        userInterface.setInputs(inputs);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(100);
        Data itemUpdated = storage.getItem(100);
        itemUpdated.updateName("NewItem");
        itemUpdated.updateAmount(10);
        itemUpdated.updatePrice(new BigDecimal("0.49"));

        Boolean test = item.getName().equals("ItemNr1") &&
                item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                item.getAmount() == 0;
        ;
        Assert.assertFalse(test);
    }
}