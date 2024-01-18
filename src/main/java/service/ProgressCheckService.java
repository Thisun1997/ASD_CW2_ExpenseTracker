package service;

import model.Category;
import model.BudgetLineItem;
import model.Expense;
import repository.CategoryRepository;
import repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgressCheckService {
    public static List<BudgetLineItem> getProgress(int month) {
        List<BudgetLineItem> lineItems = new ArrayList<>();
        List<Category> categories = CategoryRepository.getCategories(month);
        HashMap<Integer, List<Expense>> categoryMap = ExpenseRepository.getTransactionsGroupedByCategory(categories, month);
        BudgetLineItem totalBudgetLineItem = new BudgetLineItem();
        totalBudgetLineItem.setLineItemName("Total");
        totalBudgetLineItem.setLimit(1000); //get from budget repo
        for (int categoryId: categoryMap.keySet()) {
            Category category = CategoryRepository.getBudgetCategory(categoryId, month);
            String categoryName = category.getName();
            double budgetLimit = category.getBudgetLimit();
            BudgetLineItem budgetLineItem = new BudgetLineItem();
            budgetLineItem.setLineItemName(categoryName);
            budgetLineItem.setLimit(budgetLimit);
            for (Expense expense: categoryMap.get(categoryId)){
                budgetLineItem.addExpense(expense.getAmount());
            }
            totalBudgetLineItem.addComponent(budgetLineItem);
            lineItems.add(budgetLineItem);
        }
        lineItems.add(totalBudgetLineItem);
        return lineItems;
    }


    public static String addExpense(double amount, int categoryId, int month) {
        Category category = CategoryRepository.getCategory(categoryId);
        if (category == null) {
            return "Category doesn't exist";
        }
        Expense expense = new Expense.ExpenseBuilder(amount).setCategory(category).setMonth(month).build();
        ExpenseRepository.addExpense(expense);
        return "Expense added successfully";
    }

    public static String addCategoryBudget(int categoryId, int month, double budgetLimit) {
        return CategoryRepository.addCategoryBudget(categoryId, month, budgetLimit);
    }
}
