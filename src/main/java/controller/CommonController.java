package controller;

import model.BudgetLineItem;
import service.BudgetService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Scanner;

import static constants.Constants.YES;

public class CommonController {
    public static void showTransactions(YearMonth yearMonth) {
        System.out.println("*****Transactions*****");
        TransactionService.showTransactions(yearMonth);
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
            System.out.println("Category\t| Amount spent\t| Budget Limit");
            for (BudgetLineItem budgetLineItem: BudgetService.getProgress(yearMonth)) {
                System.out.println(budgetLineItem.toString());
            }
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    }
}