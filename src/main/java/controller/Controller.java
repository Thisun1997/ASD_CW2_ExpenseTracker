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

    public Controller(Scanner scanner, YearMonth yearMonth) {
        this.scanner = scanner;
        this.yearMonth = yearMonth;
    }

    public abstract void showCategories();

    public abstract int addCategory();
    public void setBudget(){
        try {
            System.out.print("Enter Budget for "+yearMonth+": ");
            BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
            String action = YES;
            if ( BudgetService.isBudgetAdded(yearMonth)) {
                System.out.print("A budget is already set to "+yearMonth+". Do you want to update it (Y/N): ");
                action = scanner.nextLine();
            }
            System.out.print(BudgetService.setBudget(budget, yearMonth, action));
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    };

    public void showProgress() {
        try {
            System.out.println("Category\t| Amount spent\t| Budget Limit");
            for (BudgetLineItem budgetLineItem: BudgetService.getProgress(yearMonth)) {
                System.out.println(budgetLineItem.toString());
            }
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    }

    public abstract void showTransactions();

    public abstract void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring);

    public abstract Category getCategoryById(int categoryId);
}
