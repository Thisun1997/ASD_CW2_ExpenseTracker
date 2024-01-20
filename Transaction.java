package model;

import java.time.LocalDate;

public abstract class Transaction implements BudgetComponent {
    protected double amount;

    //optional
    protected  Category category;

    protected LocalDate transactionDate;

    @Override
    public double getTotal() {
        return amount;
    }

    @Override
    public void addExpense(double amount) {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory(){return category;}

    abstract public String toString();

}
