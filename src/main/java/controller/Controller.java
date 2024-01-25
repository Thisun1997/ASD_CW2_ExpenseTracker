package controller;

import java.time.YearMonth;
import java.util.Scanner;

public abstract class Controller {
    protected Scanner scanner;
    protected YearMonth yearMonth;

    public Controller(Scanner scanner, YearMonth yearMonth) {
        this.scanner = scanner;
        this.yearMonth = yearMonth;
    }

    public abstract void showCategories();

    public abstract String addCategory();

    public abstract void deleteCategory();
    
    public abstract void updateCategory();

    public abstract void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring);

    public abstract List<Category> getCategories();

    public abstract void updateTransaction(YearMonth yearMonth, int transactionId, Category category, BigDecimal amount, String note, String currentDate, boolean isRecurring);
}
