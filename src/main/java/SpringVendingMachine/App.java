package SpringVendingMachine;

import SpringVendingMachine.controller.Control;
import SpringVendingMachine.servicelayer.InsufficientFundsException;
import SpringVendingMachine.servicelayer.NoItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Control controller =
                ctx.getBean("controller", Control.class);
        try {
            controller.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            controller.logAudit(e);
        }
    }
}
