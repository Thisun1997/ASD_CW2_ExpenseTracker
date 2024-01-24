package repository;

import model.Category;
import model.Expense;
import model.Transaction;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionRepository {
    private static final Map<YearMonth, List<Transaction>> transactionsMap = new HashMap<>();

    public static void addTransaction(YearMonth yearMonth, Transaction transaction) {
        transactionsMap.computeIfAbsent(yearMonth, k -> new ArrayList<>()).add(transaction);
    }

    public static List<Expense> getExpenses(YearMonth yearMonth) {
        return transactionsMap.getOrDefault(yearMonth, new ArrayList<>())
                .stream()
                .filter(transaction -> transaction instanceof Expense)
                .map(transaction -> (Expense) transaction)
                .collect(Collectors.toList());
    }

    public static HashMap<String, List<Expense>> getTransactionsGroupedByYearMonthAndCategory(List<Category> categories, YearMonth yearMonth) {
        HashMap<String, List<Expense>> categoryMap = new HashMap<>();
        for(Category category: categories){
            categoryMap.putIfAbsent(category.getName(), new ArrayList<>());
        }
        for(Expense expense: getExpenses(yearMonth)){
            String categoryName = expense.getCategory().getName();
            categoryMap.get(categoryName).add(expense);
        }
        return categoryMap;
    }

    public static List<Transaction> getTransactions(YearMonth yearMonth) {
        return transactionsMap.getOrDefault(yearMonth, new ArrayList<>());
    }
}
