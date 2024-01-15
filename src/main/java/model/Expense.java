package model;

import model.BudgetComponent;

public class Expense implements BudgetComponent {

    //required
    private double amount;

    //optional
    private final Category category;
    private int month;

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

    public Category getCategory() {
        return category;
    }

    public int getMonth() {
        return month;
    }

    private Expense(ExpenseBuilder builder){
        this.amount = builder.amount;
        this.category = builder.category;
        this.month = builder.month;
    }

    public static class ExpenseBuilder{

        //required
        private double amount;

        //optional
        private Category category;
        private int month;

        public ExpenseBuilder(double amount) {
            this.amount = amount;
        }

        public ExpenseBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public ExpenseBuilder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Expense build(){
            return new Expense(this);
        }

    }
}
