package model;

import java.math.BigDecimal;

public class Income extends Transaction{
    private IncomeCategory category;

    public Income(String date, BigDecimal amount, String note, boolean isRecurring, IncomeCategory category) {
        super(date, amount, note, isRecurring);
        this.category = category;
    }

}
