package repository;

import model.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionRepository {

    private static final List<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static List<Expense> getTransactions() {
        return expenses;
    }

    public static HashMap<Integer, List<Expense>> getTransactionsGroupedByCategory() {
        HashMap<Integer, List<Expense>> categoryMap = new HashMap<>();
        for(Expense expense: getTransactions()){
            int categoryId = expense.getCategory().getCategoryId();
            categoryMap.putIfAbsent(categoryId, new ArrayList<>());
            categoryMap.get(categoryId).add(expense);
        }
        return categoryMap;
    }
}
