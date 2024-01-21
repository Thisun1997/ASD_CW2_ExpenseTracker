package repository;

import model.Category;
import model.Expense;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {

//    private static final List<Expense> expenses = new ArrayList<>();
    private static final Map<YearMonth, List<Transaction>> transactionsMap = new HashMap<>();

//    public static void addExpense(Expense expense) {
//        expenses.add(expense);
//    }
//
//    public static List<Expense> getTransactions() {
//        return expenses;
//    }
//
//    public static HashMap<Integer, List<Expense>> getTransactionsGroupedByCategory(List<Category> categories) {
//        HashMap<Integer, List<Expense>> categoryMap = new HashMap<>();
//        for(Category category: categories){
//            categoryMap.putIfAbsent(category.getCategoryId(), new ArrayList<>());
//        }
//        for(Expense expense: getTransactions()){
//            int categoryId = expense.getCategory().getCategoryId();
//            categoryMap.get(categoryId).add(expense);
//        }
//        return categoryMap;
//    }

    public static void addTransaction(YearMonth yearMonth, Transaction transaction) {
        transactionsMap.computeIfAbsent(yearMonth, k -> new ArrayList<>()).add(transaction);
    }

//    public static List<Transaction> getTransactions(YearMonth yearMonth) {
//        return transactionsMap.getOrDefault(yearMonth, new ArrayList<>());
//    }
}
