package model;

import java.math.BigDecimal;

public class Income extends Transaction{
    private IncomeCategory category;

    public Income(String date, BigDecimal amount, String note, boolean isRecurring, IncomeCategory category, String recurringId) {
        super(date, amount, note, isRecurring, recurringId);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(IncomeCategory category){
        this.category = category;
    }

    @Override
    protected String getCategoryString() {
        return "INCOME - " + category.getName();
    }
}
