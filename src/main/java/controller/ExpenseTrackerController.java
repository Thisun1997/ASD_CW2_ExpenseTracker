package controller;

import exception.CategoryException;
import exception.ExpenseTrackerException;
import model.BudgetLineItem;
import model.Category;
import service.*;

public class ExpenseTrackerController {

    public static void addExpense(double amount, int categoryId, int month) {
        System.out.println(ProgressCheckService.addExpense(amount, categoryId, month));
    }

    public static void addCategory(String name) {
        System.out.println("Category added successfully with category Id: " + CategoryService.addCategory(name));
    }

    public static void editCategory(int categoryId, String newName) {
        try{
            CategoryService.editCategory(categoryId, newName);
            System.out.println("Category edited successfully");
        } catch (CategoryException.CategoryNotFoundException | CategoryException.CategoryHasExpensesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExpenseTrackerException e) {
            System.out.println("Error editing category: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error editing category");
        }
    }

    public static void deleteCategory(int categoryId) {
        try {
            CategoryService.deleteCategory(categoryId);
            System.out.println("Category deleted successfully");
        } catch (CategoryException.CategoryNotFoundException | CategoryException.CategoryHasExpensesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExpenseTrackerException e) {
            System.out.println("Error deleting category: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error deleting category");
        }
    }

    public static void deleteCategoryExpense(int categoryId, int month) {
        try {
            CategoryService.deleteCategoryExpenses(categoryId, month);
            System.out.println("Delete Expense for the Category Successfully");
        } catch (CategoryException.CategoryNotFoundException | CategoryException.CategoryHasExpensesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExpenseTrackerException e) {
            System.out.println("Error deleting category Expenses: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error deleting category expenses");
        }
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
        for(Category cat: CategoryService.getCategories(month)){
            System.out.println(cat.getCategoryId() + " | "
                    + cat.getName() + " | "
                    + cat.getBudgetLimit());
        };
    }

    public static void getAllCategories() {
        for(Category cat: CategoryService.getAllCategories()){
            System.out.println(cat.getCategoryId() + " | " + cat.getName());
        }
    }

    public static void addCategoryBudget(int categoryId, int month, double budgetLimit) {
        System.out.println(ProgressCheckService.addCategoryBudget(categoryId, month, budgetLimit));
    }
}
