package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Transaction {
    private static final AtomicLong uniqueIdCounter = new AtomicLong(1);

    private final String uniqueId;
    private String date;
    private BigDecimal amount;
    private String note;
    private boolean isRecurring;
    private String recurringId;

    public Transaction(String date, BigDecimal amount, String note, boolean isRecurring, String recurringId){
        this.uniqueId = generateUniqueId();
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.isRecurring = isRecurring;
        this.recurringId = recurringId;
    }

    public String getId() { return uniqueId; }

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

    public String getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(String recurringId) {
        this.recurringId = recurringId;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, Date: %s, Amount: %s, Note: %s, Category: %s, InRecurring: %s",uniqueId, date, amount, note, getCategoryString(), isRecurring);
    }

    protected abstract String getCategoryString();

    private static String generateUniqueId() {
        return String.valueOf(uniqueIdCounter.getAndIncrement());
    }
}
