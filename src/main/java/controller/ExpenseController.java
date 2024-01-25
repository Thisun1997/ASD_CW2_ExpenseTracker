package controller;

import model.ExpenseCategory;
import service.ExpenseCategoryService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Scanner;

public class ExpenseController extends Controller{
    private static ExpenseController expenseController = null;

    private ExpenseController(Scanner scanner,YearMonth yearMonth){
        super(scanner,yearMonth);
    }
    public static synchronized Controller getInstance(Scanner scanner,YearMonth yearMonth){
        if(expenseController == null){
            expenseController = new ExpenseController(scanner, yearMonth);
        }
        return expenseController;
    }

    @Override
    public void showCategories() {
        String tab = "\t";
        System.out.println("*************** Expense Categories ***************");
        System.out.println("Index\t"+"Category"+tab.repeat(3)+"Budget for "+yearMonth.toString());
        int index =1;
        for(ExpenseCategory cat: ExpenseCategoryService.getCategories()){
            String name = cat.getName();
            System.out.println(index + tab.repeat(2) + name + tab.repeat(6-(name.length()/4)) + cat.getBudgetLimit(yearMonth));
            index++;
        }
    }

    @Override
    public String addCategory() {
        System.out.print("Please enter category name:");
        String name = scanner.nextLine();
        System.out.print("Enter Budged for "+name+":");
        BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        ExpenseCategoryService.addCategory(name,yearMonth,budget);
        return name;
    }

    @Override
    public void deleteCategory() {
        int index = Utils.getCommand(
                scanner,"Please enter the category index:",1, ExpenseCategoryService.getCategoryCount());
        index--;
        int transactionCount =
                TransactionService.getTransactionCount(ExpenseCategoryService.getCategory(index));
        System.out.println("This will delete "+ transactionCount + " transactions.\n"+
                "Press 1 to continue\n"+
                "Press 2 to abort");
        int choice = Utils.getCommand(scanner, "Enter Command:",1,2);
        if(choice == 1){
            TransactionService.removeTransactions(ExpenseCategoryService.getCategory(index));
            ExpenseCategoryService.deleteCategory(index);
        }
    }

    @Override
    public void updateCategory() {
        int index = Utils.getCommand(
                scanner,"Please enter the category index:",1, ExpenseCategoryService.getCategoryCount());
        index--;
        ExpenseCategory expenseCategory = ExpenseCategoryService.getCategory(index);
        boolean exitLoop = false;
        while (!exitLoop){
            System.out.println("Category Name: "+ expenseCategory.getName() + "\n"+
                    "Budget for "+ yearMonth.toString() + ": " + expenseCategory.getBudgetLimit(yearMonth));
            System.out.println(
                    "1. Edit Name\n" +
                    "2. Edit Budget\n" +
                    "3. Exit");
            int choice = Utils.getCommand(scanner,"Enter Command:",1,3);
            switch (choice){
                case 1 -> {
                    System.out.print("Please enter new category name:");
                    String name = scanner.nextLine();
                    ExpenseCategoryService.updateExpenseCategory(index, name);
                }
                case 2 -> {
                    System.out.print("Enter Budged for "+expenseCategory.getName()+":");
                    BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
                    ExpenseCategoryService.updateExpenseCategory(index, yearMonth,                                                     budget);
                }
                default -> exitLoop = true;
            }
        }
    }


}
