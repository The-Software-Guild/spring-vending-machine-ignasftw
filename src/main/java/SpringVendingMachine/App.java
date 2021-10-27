package SpringVendingMachine;

import SpringVendingMachine.Controller.Control;
import SpringVendingMachine.DAO.DataStorage;
import SpringVendingMachine.ServiceLayer.InsufficientFundsException;
import SpringVendingMachine.ServiceLayer.NoItemInventoryException;
import SpringVendingMachine.View.UserInterface;
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
