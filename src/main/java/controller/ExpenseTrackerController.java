package controller;

import model.BudgetLineItem;
import model.Category;
import service.ProgressCheckService;

public class ExpenseTrackerController {

    public static void addExpense(double amount, int categoryId) {
        System.out.println(ProgressCheckService.addExpense(amount, categoryId));
    }

    public static void addCategory(String name, double budgetLimit) {
        System.out.println("Category added successfully with category Id: " + ProgressCheckService.addCategory(name, budgetLimit));
    }

    public static void getProgress() {
        System.out.println("Category Name | Budget Limit | Spent Amount");
        for (BudgetLineItem lineItem: ProgressCheckService.getProgress()) {
            System.out.println(lineItem.getLineItemName() + " | "
                    + lineItem.getLimit() + " | "
                    + lineItem.getTotal());
        }
    }

    public static void getCategories() {
        for(Category cat: ProgressCheckService.getCategories()){
            System.out.println(cat.getCategoryId() + " | "
            + cat.getName() + " | "
            + cat.getBudgetLimit());
        };
    }
}
