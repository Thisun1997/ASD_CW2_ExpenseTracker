package controller;

import model.Transaction;
import service.TransactionService;

import java.time.YearMonth;
import java.util.List;

public class CommonController {
    public static void showTransactions(YearMonth yearMonth) {
        System.out.println("*****Transactions*****");
        TransactionService.showTransactions(yearMonth);
    }

    public static List<Transaction> getTransactions(YearMonth yearMonth) {
        return TransactionService.getTransactions(yearMonth);
    }

    public static Transaction getTransactionById(YearMonth yearMonth, int transactionId) {
        return TransactionService.getTransactionById(yearMonth, transactionId);
    }

    public static void deleteTransaction(int transactionId, YearMonth yearMonth) {
        TransactionService.deleteTransaction(yearMonth, transactionId);
        System.out.println("Transaction deleted successfully.");
    }
}
