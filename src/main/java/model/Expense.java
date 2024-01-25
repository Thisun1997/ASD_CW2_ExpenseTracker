package model;

import java.math.BigDecimal;

public class Expense extends Transaction implements BudgetComponent {

    //optional
    private ExpenseCategory category;

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

    public void setCategory(ExpenseCategory category){
        this.category = category;
    }

    @Override
    protected String getCategoryString() {
        return "EXPENSE - " + category.getName();
    }

    private Expense(ExpenseBuilder builder){
        super(builder.date, builder.amount, builder.note, builder.isRecurring, builder.recurringId);
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
        private String recurringId;


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

        public ExpenseBuilder setRecurringId(String recurringId) {
            this.recurringId = recurringId;
            return this;
        }

        public Expense build(){
            return new Expense(this);
        }

    }
}