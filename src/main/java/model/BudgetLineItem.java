package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BudgetLineItem implements BudgetComponent {

    private String lineItemName;
    private BigDecimal limit;
    private final List<BudgetComponent> components = new ArrayList<>();

    @Override
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (BudgetComponent component : components) {
            total = total.add(component.getTotal());
        }
        return total;
    }

    public void addComponent(BudgetComponent component) {
        components.add(component);
    }

    public void addExpense(BigDecimal amount) {
        Expense expense = new Expense.ExpenseBuilder(amount).build();
        components.add(expense);
    }

    public String getLineItemName() {
        return lineItemName;
    }

    public void setLineItemName(String lineItemName) {
        this.lineItemName = lineItemName;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        String tab = "\t";
        BigDecimal total = getTotal();
        return lineItemName + tab.repeat(6-(lineItemName.length()/4)) + getTotal() + tab.repeat(6-(total.toString().length()/4)) + limit;
    }
}
