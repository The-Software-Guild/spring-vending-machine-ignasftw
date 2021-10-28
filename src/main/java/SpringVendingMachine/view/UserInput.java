package SpringVendingMachine.view;

import java.math.BigDecimal;

public interface UserInput {
    BigDecimal moneyUserInput();

    int getIntUserInput(int max);
}
