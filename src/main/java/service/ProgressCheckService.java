package service;

import model.Category;
import model.BudgetLineItem;
import model.Expense;
import repository.CategoryRepository;
import repository.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgressCheckService {
    public static List<BudgetLineItem> getProgress() {
        List<BudgetLineItem> lineItems = new ArrayList<>();
        List<Category> categories = CategoryRepository.getCategories();
        HashMap<Integer, List<Expense>> categoryMap = TransactionRepository.getTransactionsGroupedByCategory(categories);
        BudgetLineItem totalBudgetLineItem = new BudgetLineItem();
        totalBudgetLineItem.setLineItemName("Total");
        totalBudgetLineItem.setLimit(1000);
        for (int categoryId: categoryMap.keySet()) {
            Category category = CategoryRepository.getCategory(categoryId);
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


    public static String addExpense(double amount, int categoryId) {
        Category category = CategoryRepository.getCategory(categoryId);
        if (category == null) {
            return "Category doesn't exist";
        }
        Expense expense = new Expense.ExpenseBuilder(amount).setCategory(category).build();
        TransactionRepository.addExpense(expense);
        return "Expense added successfully";
    }

    public static int addCategory(String name, double budgetLimit) {
        return CategoryRepository.addCategory(new Category(name, 0, budgetLimit));
    }

    public static List<Category> getCategories() {
        return CategoryRepository.getCategories();
    }
}
