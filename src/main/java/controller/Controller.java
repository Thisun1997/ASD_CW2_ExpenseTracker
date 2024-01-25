package controller;

import model.BudgetLineItem;
import model.Category;
import repository.BudgetRepository;
import service.BudgetService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

import static constants.Constants.YES;

public abstract class Controller {
    protected Scanner scanner;
    protected YearMonth yearMonth;

    public Controller(Scanner scanner, YearMonth yearMonth){
        this.scanner = scanner;
        this.yearMonth = yearMonth;
    }

    public abstract void showCategories();

    public abstract int addCategory();

    public abstract void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring);

    public abstract Category getCategoryById(int categoryId);

    public abstract List<Category> getCategories();

    public abstract void updateTransaction(YearMonth yearMonth, int transactionId, Category category, BigDecimal amount, String note, String currentDate, boolean isRecurring);
    public abstract String addCategory();
    public abstract void deleteCategory();
    public abstract void updateCategory();
}
