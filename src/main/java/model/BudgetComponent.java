package model;

import java.math.BigDecimal;

public interface BudgetComponent {
    BigDecimal getTotal();
    void addExpense(BigDecimal amount);
}
