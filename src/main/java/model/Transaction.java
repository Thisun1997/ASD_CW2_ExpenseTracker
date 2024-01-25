package model;

public class Transaction implements BudgetComponent {

    //required
    private double amount;

    //optional
    private final Category category;

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

    private Transaction(TransactionBuilder builder){
        this.amount = builder.amount;
        this.category = builder.category;
    }

    public static class TransactionBuilder{

        //required
        private double amount;

        //optional
        private Category category;

        public TransactionBuilder(double amount) {
            this.amount = amount;
        }

        public TransactionBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }

    }
}
