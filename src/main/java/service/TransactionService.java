package service;

import model.*;
import repository.TransactionRepository;
import util.RecurringIdGenerator;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransactionService {

    private static void submitTransactionForNextMonths(YearMonth yearMonth, String date, BigDecimal amount, String note, Category category, String recurringId, boolean isExpense) {
        for (int i = 0; i < 12; i++) {
            YearMonth nextYearMonth = yearMonth.plusMonths(i);
            submitTransaction(nextYearMonth, amount, category,date, note, true, recurringId, isExpense);
        }
    }

    private static void submitTransaction(YearMonth nextYearMonth, BigDecimal amount, Category category, String date, String note, boolean isRecurring, String recurringId, boolean isExpense) {
        Transaction transaction;
        if (isExpense) {
            transaction = new Expense.ExpenseBuilder(amount).setCategory((ExpenseCategory) category).setDate(date).setNote(note).setIsRecurring(isRecurring).setRecurringId(recurringId).build();
        } else {
            transaction = new Income(date, amount, note, isRecurring, (IncomeCategory) category, recurringId);
        }
        TransactionRepository.addTransaction(nextYearMonth, transaction);
    }

    public static void addTransaction(YearMonth yearMonth, String date, BigDecimal amount, String note, Category category, boolean isRecurring, boolean isExpense) {
        String recurringId = null; // Initialize recurringId to null by default

        if (isRecurring) {
            recurringId = String.valueOf(RecurringIdGenerator.getNextTransactionId());
        }

        if (isRecurring) {
            // Adding transaction for next 12 months with current month
            submitTransactionForNextMonths(yearMonth, date, amount, note, category, recurringId, isExpense);
        } else {
            submitTransaction(yearMonth, amount, category, date, note, false, null, isExpense);
        }
    }

    public static void showTransactions(YearMonth yearMonth) {
        List<Transaction> transactions = TransactionRepository.getTransactions(yearMonth);

        if (transactions.isEmpty()) {
            System.out.println("No transactions for the selected year and month.");
        } else {
            transactions.sort(Comparator.comparing(Transaction::getDate));
            System.out.println("*****Transactions for " + yearMonth + "*****");
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
        }
    }

    public static List<Transaction> getTransactions(YearMonth yearMonth) {
        return TransactionRepository.getTransactions(yearMonth);
    }

    public static Map<YearMonth, List<Transaction>> getTransactionsMap() {
        return TransactionRepository.getTransactionsMap();
    }

    public static void deleteTransaction(YearMonth yearMonth, int transactionId) {
        TransactionRepository.deleteTransaction(yearMonth, transactionId);
    }

    public static Transaction getTransactionById(YearMonth yearMonth, int transactionId) {
        return TransactionRepository.getTransactionById(yearMonth, transactionId);
    }

    public static void updateTransaction(YearMonth yearMonth, int transactionId, Category category, BigDecimal amount, String note, String currentDate, boolean isRecurring, boolean isExpense) {
        Transaction transaction = TransactionRepository.getTransactionById(yearMonth, transactionId);

        if (transaction != null) {
            boolean tempIsRecurring = transaction.isRecurring();

            // Update the current month transaction
            if (isExpense) {
                ((Expense) transaction).setCategory((ExpenseCategory) category);
            } else {
                ((Income) transaction).setCategory((IncomeCategory) category);
            }
            transaction.setAmount(amount);
            transaction.setNote(note);
            transaction.setDate(currentDate);
            transaction.setRecurring(isRecurring);

            if (tempIsRecurring && !isRecurring) {
                // If changing from recurring to non-recurring, delete transactions for next months
                deleteRecurringTransaction(yearMonth, transaction.getRecurringId());
            } else if (!tempIsRecurring && isRecurring) {
                transaction.setRecurringId(String.valueOf(RecurringIdGenerator.getNextTransactionId()));
                // If changing from non-recurring to recurring, add transactions for next 12 months without current month
                submitTransactionForNextMonths(yearMonth.plusMonths(1), currentDate, amount, note, category, transaction.getRecurringId(), isExpense);
            } else if (tempIsRecurring) {
                // If remaining recurring, update transactions for upcoming months with the new fields
                updateRecurringTransactions(yearMonth, transaction.getRecurringId(), category, amount, note, currentDate, isExpense);
            }
        }
    }

    public static void deleteRecurringTransaction(YearMonth yearMonth, String recurringId) {
        TransactionRepository.deleteRecurringTransaction(yearMonth, recurringId);
    }

    private static void updateRecurringTransactions(YearMonth yearMonth, String recurringId, Category category, BigDecimal amount, String note, String currentDate, boolean isExpense) {
        YearMonth nextYearMonth = yearMonth.plusMonths(1);
        boolean found;

        do {
            found = false;
            List<Transaction> transactions = getTransactions(nextYearMonth);

            for (Transaction transaction : transactions) {
                if (transaction.isRecurring() && recurringId.equals(transaction.getRecurringId())) {
                    TransactionRepository.updateRecurringTransaction(transaction, category, amount, note, currentDate, isExpense);
                    found = true;
                    break;
                }
            }

            // Move to the next month
            nextYearMonth = nextYearMonth.plusMonths(1);
        } while (found);
    }

    public static int getTransactionCount(Category category){
        return TransactionRepository.getTransactionCount(category);
    }
    public static void removeTransactions(Category category){
        TransactionRepository.removeTransactions(category);
    }
}
