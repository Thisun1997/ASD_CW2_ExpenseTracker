package service;

import model.*;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;

public class TransactionService {
    public static void addTransaction(YearMonth yearMonth, String date, BigDecimal amount, String note, Category category, boolean isRecurring, boolean isExpense) {
        Transaction transaction;
        if (isExpense) {
            transaction = new Expense.ExpenseBuilder(amount).setCategory((ExpenseCategory) category).setDate(date).setNote(note).setIsRecurring(isRecurring).build();
        } else {
            transaction = new Income(date, amount, note, isRecurring, (IncomeCategory) category);
        }

        if (isRecurring) {
            // Adding transaction for next 12 months
            for (int i = 0; i < 12; i++) {
                YearMonth nextYearMonth = yearMonth.plusMonths(i);
                TransactionRepository.addTransaction(nextYearMonth, transaction);
            }
        } else {
            TransactionRepository.addTransaction(yearMonth, transaction);
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
}
