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
    private static final Map<YearMonth, List<Transaction>> transactionsMap = new HashMap<>();

    public static void addTransaction(YearMonth yearMonth, Transaction transaction) {
        transactionsMap.computeIfAbsent(yearMonth, k -> new ArrayList<>()).add(transaction);
    }

    public static List<Transaction> getTransactions(YearMonth yearMonth) {
        return transactionsMap.getOrDefault(yearMonth, new ArrayList<>());
    }
}
