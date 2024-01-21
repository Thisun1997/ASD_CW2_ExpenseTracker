package controller;

import model.Category;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public abstract class Controller {
    protected Scanner scanner;
    protected YearMonth yearMonth;

    public Controller(Scanner scanner, YearMonth yearMonth) {
        this.scanner = scanner;
        this.yearMonth = yearMonth;
    }

    public abstract void showCategories();

    public abstract int addCategory();

    public abstract void showTransactions();

    public abstract void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring);

    public abstract Category getCategoryById(int categoryId);
}
