package controller;

import model.BudgetLineItem;
import model.Category;
import service.ProgressCheckService;

public class ExpenseTrackerController {

    public static void addExpense(double amount, int categoryId, int month) {
        System.out.println(ProgressCheckService.addExpense(amount, categoryId, month));
    }

    public static void addCategory(String name) {
        System.out.println("Category added successfully with category Id: " + ProgressCheckService.addCategory(name));
    }

    public static void getProgress(int month) {
        System.out.println("Category Name | Budget Limit | Spent Amount");
        for (BudgetLineItem lineItem: ProgressCheckService.getProgress(month)) {
            System.out.println(lineItem.getLineItemName() + " | "
                    + lineItem.getLimit() + " | "
                    + lineItem.getTotal());
        }
    }

    public static void getCategories(int month) {
        for(Category cat: ProgressCheckService.getCategories(month)){
            System.out.println(cat.getCategoryId() + " | "
                    + cat.getName() + " | "
                    + cat.getBudgetLimit());
        };
    }

    public static void addCategoryBudget(int categoryId, int month, double budgetLimit) {
        System.out.println(ProgressCheckService.addCategoryBudget(categoryId, month, budgetLimit));
    }
}
