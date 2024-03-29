package controller;

import model.Category;
import model.IncomeCategory;
import service.IncomeCategoryService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Scanner;

public class IncomeController extends Controller{
    private static final HashMap<YearMonth, IncomeController> yearMonthIncomeControllerMap = new HashMap<>();

    private IncomeController(Scanner scanner, YearMonth yearMonth){
        super(scanner,yearMonth);
    }
    public static Controller getInstance(Scanner scanner,YearMonth yearMonth){
        if(yearMonthIncomeControllerMap.containsKey(yearMonth)){
            return yearMonthIncomeControllerMap.get(yearMonth);
        } else {
            IncomeController incomeController = new IncomeController(scanner, yearMonth);
            yearMonthIncomeControllerMap.put(yearMonth, incomeController);
            return incomeController;
        }
    }

    @Override
    public void showCategories() {
        System.out.println("*************** Income Categories ***************");
        System.out.println("Index\tCategory");
        int index = 1;
        for(IncomeCategory cat: IncomeCategoryService.getCategories()){
            System.out.println(index + "\t\t" + cat.getName());
            index++;
        }
    }

    @Override
    public String addCategory() {
        System.out.print("Please enter category name:");
        String name = scanner.nextLine();
        IncomeCategoryService.addCategory(name);
        return name;
    }

    @Override
    public void deleteCategory() {
        int index = Utils.getCommand(
                scanner,"Please enter the category index:",1, IncomeCategoryService.getCategoryCount());
        index--;
        int transactionCount =
                TransactionService.getTransactionCount(IncomeCategoryService.getCategory(index));
        System.out.println("This will delete "+ transactionCount + " transactions.\n"+
                "Press 1 to continue\n"+
                "Press 2 to abort");
        int choice = Utils.getCommand(scanner, "Enter Command:",1,2);
        if(choice == 1){
            TransactionService.removeTransactions(IncomeCategoryService.getCategory(index));
            IncomeCategoryService.deleteCategory(index);
        }
    }

    @Override
    public void updateCategory() {
        int index = Utils.getCommand(
                scanner,"Please enter the category index:",1, IncomeCategoryService.getCategoryCount());
        index--;
        IncomeCategory incomeCategory = IncomeCategoryService.getCategory(index);
        boolean exitLoop = false;
        while (!exitLoop){
            System.out.println("Category Name: "+ incomeCategory.getName());
            System.out.println(
                    "1. Edit Name\n" +
                    "2. Exit");
            int choice = Utils.getCommand(scanner,"Enter Command:",1,2);
            if (choice == 1) {
                System.out.print("Please enter new category name:");
                String name = scanner.nextLine();
                IncomeCategoryService.updateIncomeCategory(index, name);
            } else {
                exitLoop = true;
            }
        }
    }
    @Override
    public void updateTransaction(YearMonth yearMonth, int transactionId, Category category, BigDecimal amount, String note, String currentDate, boolean isRecurring) {
        try {
            TransactionService.updateTransaction(yearMonth, transactionId, category, amount, note, currentDate, isRecurring, false);
            System.out.println("Income Transaction updated successfully!");
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    }


    @Override
    public void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring) {
        try {
            TransactionService.addTransaction(yearMonth, currentDate, amount, note, selectedCategory, isRecurring, false);
            System.out.println("Income Transaction added successfully!");
        } catch (Exception e) {
            System.out.print("Error :"+e.getMessage());
        }
    }

    @Override
    public Category getCategory(int index) {
        return IncomeCategoryService.getCategory(index);
    }

    @Override
    public int getCategoriesSize() {
        return IncomeCategoryService.getCategories().size();
    }
}
