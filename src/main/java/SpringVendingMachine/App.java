package SpringVendingMachine;

import SpringVendingMachine.controller.Control;
import SpringVendingMachine.servicelayer.InsufficientFundsException;
import SpringVendingMachine.servicelayer.NoItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
/*        UserInterface userInterface = new UserInterface();
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface,userInterface,storage);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            control.logAudit(e);
        }*/

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Control controller =
                ctx.getBean("controller",Control.class);
        try {
            controller.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            controller.logAudit(e);
        }
    }
}
