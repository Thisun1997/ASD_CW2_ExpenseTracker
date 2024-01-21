package model;

import java.math.BigDecimal;

public class Expense extends Transaction implements BudgetComponent {

    //optional
    private final ExpenseCategory category;

    @Override
    public void addExpense(BigDecimal amount) {
    }

    @Override
    public BigDecimal getTotal() {
        return this.getAmount();
    }

    public Category getCategory() {
        return category;
    }

    @Override
    protected String getCategoryString() {
        return "EXPENSE - " + category.getName();
    }

    private Expense(ExpenseBuilder builder){
        super(builder.date, builder.amount, builder.note, builder.isRecurring);
        this.category = builder.category;
    }

    public static class ExpenseBuilder{

        //required
        private BigDecimal amount;

        //optional
        private ExpenseCategory category;
        private String date;
        private String note;
        private boolean isRecurring;


        public ExpenseBuilder(BigDecimal amount) {
            this.amount = amount;
        }

        public ExpenseBuilder setCategory(ExpenseCategory category) {
            this.category = category;
            return this;
        }

        public ExpenseBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public ExpenseBuilder setNote(String note) {
            this.note = note;
            return this;
        }
        public ExpenseBuilder setIsRecurring(boolean isRecurring) {
            this.isRecurring = isRecurring;
            return this;
        }

        public Expense build(){
            return new Expense(this);
        }

    }
}
