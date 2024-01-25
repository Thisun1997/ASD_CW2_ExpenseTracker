package service;

import model.Category;
import model.ExpenseCategory;
import repository.CategoryRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

public class ExpenseCategoryService{
    private static List<ExpenseCategory> expenseCategories;

    public static  List<ExpenseCategory> getCategories() {
        expenseCategories = CategoryRepository.getExpenseCategories();
        return expenseCategories;
    }

    public static void addCategory(String name,YearMonth yearMonth, BigDecimal budget) {
        CategoryRepository.addCategory(new ExpenseCategory(name,yearMonth,budget));
    }
    public static int getCategoryCount(){
        return expenseCategories.size();
    }

    public static ExpenseCategory getCategory(int index){ return expenseCategories.get(index);}

    public static void deleteCategory(int index){
        CategoryRepository.deleteCategory(expenseCategories.get(index));
        expenseCategories = CategoryRepository.getExpenseCategories();
    }

    public static void updateExpenseCategory(int index, String name) {
        CategoryRepository.updateExpenseCategory(expenseCategories.get(index),name);
        expenseCategories = CategoryRepository.getExpenseCategories();
    }
    public static void updateExpenseCategory(int index, YearMonth yearMonth, BigDecimal budget) {
        CategoryRepository.updateExpenseCategory(expenseCategories.get(index), yearMonth, budget);
        expenseCategories = CategoryRepository.getExpenseCategories();
    }

    public static Category getCategoryById(int categoryId) {
        for (Category category : getCategories()) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        System.out.println("Expense category not found with ID: " + categoryId);
        return null;
    }
}
