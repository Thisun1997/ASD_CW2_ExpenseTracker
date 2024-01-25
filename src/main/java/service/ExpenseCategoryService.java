package service;

import model.Category;
import model.ExpenseCategory;
import repository.CategoryRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

public class ExpenseCategoryService{
    private static List<Category> expenseCategories;

    public static  List<Category> getCategories() {
        expenseCategories = CategoryRepository.getExpenseCategories();
        return expenseCategories;
    }

    public static void addCategory(String name,YearMonth yearMonth, BigDecimal budget) {
        CategoryRepository.addCategory(new ExpenseCategory(name,yearMonth,budget));
    }
    public static int getCategoryCount(){
        return expenseCategories.size();
    }

    public static Category getCategory(int index){ return expenseCategories.get(index);}

    public static void deleteCategory(int index){
        CategoryRepository.deleteCategory((ExpenseCategory) expenseCategories.get(index));
        expenseCategories = CategoryRepository.getExpenseCategories();
    }

    public static void updateExpenseCategory(int index, String name) {
        CategoryRepository.updateExpenseCategory((ExpenseCategory) expenseCategories.get(index),name);
        expenseCategories = CategoryRepository.getExpenseCategories();
    }
    public static void updateExpenseCategory(int index, YearMonth yearMonth, BigDecimal budget) {
        CategoryRepository.updateExpenseCategory((ExpenseCategory) expenseCategories.get(index), yearMonth, budget);
        expenseCategories = CategoryRepository.getExpenseCategories();
    }
}
