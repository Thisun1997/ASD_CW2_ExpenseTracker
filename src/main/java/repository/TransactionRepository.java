package repository;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class TransactionRepository {
    private static final Map<YearMonth, List<Transaction>> transactionsMap = new HashMap<>();

    public static Map<YearMonth, List<Transaction>> getTransactionsMap() {
        return transactionsMap;
    }

    public static void addTransaction(YearMonth yearMonth, Transaction transaction) {
        transactionsMap.computeIfAbsent(yearMonth, k -> new ArrayList<>()).add(transaction);
    }

    public static List<Transaction> getTransactions(YearMonth yearMonth) {
        return transactionsMap.getOrDefault(yearMonth, new ArrayList<>());
    }

    public static Transaction getTransactionById(YearMonth yearMonth, int transactionId) {
        List<Transaction> transactions = getTransactions(yearMonth);
        return transactions.stream()
                .filter(transaction -> Integer.parseInt(transaction.getId()) == transactionId)
                .findFirst()
                .orElse(null);
    }

    public static void deleteTransaction(YearMonth yearMonth, int transactionId) {
        List<Transaction> transactions = getTransactions(yearMonth);

        if (transactions.isEmpty()) {
            System.out.println("No transactions for the selected year and month.");
            return;
        }

        boolean found = false;
        Iterator<Transaction> iterator = transactions.iterator();

        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            if (Integer.parseInt(transaction.getId()) == transactionId) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Transaction not found. No changes made.");
        }
    }

    public static void deleteRecurringTransaction(YearMonth startYearMonth, String recurringId) {
        YearMonth nextYearMonth = startYearMonth.plusMonths(1); // Start from the next month
        boolean found;

        do {
            found = false;
            List<Transaction> transactions = getTransactions(nextYearMonth);
            Iterator<Transaction> iterator = transactions.iterator();

            while (iterator.hasNext()) {
                Transaction transaction = iterator.next();
                if (transaction.isRecurring() && recurringId.equals(transaction.getRecurringId())) {
                    iterator.remove();
                    found = true;
                    break;
                }
            }

            // Move to the next month
            nextYearMonth = nextYearMonth.plusMonths(1);
        } while (found);
    }


    public static void updateRecurringTransaction(Transaction transaction, Category category, BigDecimal amount, String note, String currentDate, boolean isExpense) {
        if (isExpense) {
            ((Expense) transaction).setCategory((ExpenseCategory) category);
        } else {
            ((Income) transaction).setCategory((IncomeCategory) category);
        }
        transaction.setAmount(amount);
        transaction.setNote(note);
        transaction.setDate(currentDate);
    }
}
