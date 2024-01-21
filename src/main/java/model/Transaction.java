package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Transaction {
    private String date;
    private BigDecimal amount;
    private String note;
    private boolean isRecurring;

    public Transaction(String date, BigDecimal amount, String note, boolean isRecurring){
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.isRecurring = isRecurring;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    @Override
    public String toString() {
        return String.format("Date: %s, Amount: %s, Note: %s, Category: %s, InRecurring: %s", date, amount, note, getCategoryString(), isRecurring);
    }

    protected abstract String getCategoryString();
}
