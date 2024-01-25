package controller;

import model.Category;
import service.IncomeCategoryService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class IncomeController extends Controller{
    private static IncomeController incomeController= null;

    private IncomeController(Scanner scanner, YearMonth yearMonth){
        super(scanner,yearMonth);
    }
    public static Controller getInstance(Scanner scanner,YearMonth yearMonth){
        if(incomeController == null){
            incomeController = new IncomeController(scanner,yearMonth);
        }
        return incomeController;
    }

    @Override
    public void showCategories() {
        System.out.println("*************** Income Categories ***************");
        for(Category cat: IncomeCategoryService.getCategories()){
            System.out.println(cat.toString());
        }
    }

    @Override
    public int addCategory() {
        System.out.print("Please enter category name: ");
        String name = scanner.nextLine();
        return IncomeCategoryService.addCategory(name);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return IncomeCategoryService.getCategoryById(categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return IncomeCategoryService.getCategories();
    }

    @Override
    public void updateTransaction(YearMonth yearMonth, int transactionId, Category category, BigDecimal amount, String note, String currentDate, boolean isRecurring) {
        TransactionService.updateTransaction(yearMonth, transactionId, category, amount, note, currentDate,isRecurring, false);
    }


    @Override
    public void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring) {
        TransactionService.addTransaction(yearMonth, currentDate, amount, note, selectedCategory, isRecurring, false);
        System.out.println("Income Transaction added successfully!");
    }
}
