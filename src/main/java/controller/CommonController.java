package controller;

import model.Transaction;
import model.BudgetLineItem;
import service.BudgetService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

import static constants.Constants.YES;

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

    public static void setBudget(Scanner scanner, YearMonth yearMonth){
        try {
            System.out.print("Enter Budget for "+yearMonth+": ");
            BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
            String action = YES;
            if ( BudgetService.isBudgetAdded(yearMonth)) {
                System.out.print("A budget is already set to "+yearMonth+". Do you want to update it (Y/N): ");
                action = scanner.nextLine();
            }
            System.out.print(BudgetService.setBudget(budget, yearMonth, action));
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    };

    public static void showProgress(YearMonth yearMonth) {
        try {
            String tab = "\t";
            System.out.println("Budget Progress for "+yearMonth.getYear()+"-"+yearMonth.getMonth());
            System.out.println("Category" + tab.repeat(4) + "Amount spent" + tab.repeat(3) + "Budget Limit");
            for (BudgetLineItem budgetLineItem: BudgetService.getProgress(yearMonth)) {
                System.out.println(budgetLineItem.toString());
            }
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    }
}
