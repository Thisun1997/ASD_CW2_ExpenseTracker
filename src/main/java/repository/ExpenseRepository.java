package repository;

import model.Category;
import model.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseRepository {

    private static final List<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static List<Expense> getTransactions(int month) {
        return expenses.stream().filter(expense -> expense.getMonth() == month).toList();
    }

    public static HashMap<Integer, List<Expense>> getTransactionsGroupedByCategory(List<Category> categories, int month) {
        HashMap<Integer, List<Expense>> categoryMap = new HashMap<>();
        for(Category category: categories){
            categoryMap.putIfAbsent(category.getCategoryId(), new ArrayList<>());
        }
        for(Expense expense: getTransactions(month)){
            int categoryId = expense.getCategory().getCategoryId();
            categoryMap.get(categoryId).add(expense);
        }
        return categoryMap;
    }
}
