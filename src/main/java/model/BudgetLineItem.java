package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BudgetLineItem implements BudgetComponent {

    private String lineItemName;
    private double limit;
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

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
