package controller;

import model.Category;
import model.ExpenseCategory;
import service.ExpenseCategoryService;
import service.IncomeCategoryService;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
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
        System.out.println("*************** Expense Categories ***************");
        for(Category cat: ExpenseCategoryService.getCategories()){
            System.out.println(((ExpenseCategory)cat).toString(yearMonth));
        }
    }

    @Override
    public int addCategory() {
        System.out.print("Please enter category name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Budged for "+name+": ");
        BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        return ExpenseCategoryService.addCategory(name,budget,yearMonth);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return ExpenseCategoryService.getCategoryById(categoryId);
    }

    @Override
    public void showTransactions() {
    }

    @Override
    public List<Category> getCategories() {
        return ExpenseCategoryService.getCategories();
    }


    @Override
    public void addTransaction(YearMonth yearMonth, String currentDate, BigDecimal amount, String note, Category selectedCategory, boolean isRecurring) {
        TransactionService.addTransaction(yearMonth, currentDate, amount, note, selectedCategory, isRecurring, true);
        System.out.println("Expense Transaction added successfully!");
    }
}
