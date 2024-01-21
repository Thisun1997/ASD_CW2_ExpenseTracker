package controller;

import service.TransactionService;

import java.time.YearMonth;

public class CommonController {
    public static void showTransactions(YearMonth yearMonth) {
        System.out.println("*****Transactions*****");
        TransactionService.showTransactions(yearMonth);
    }
}
