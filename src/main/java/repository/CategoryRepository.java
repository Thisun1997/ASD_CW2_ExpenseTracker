package repository;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static final List<Category> incomeCategories = new ArrayList<>();
    private static final List<Category> expenseCategories = new ArrayList<>();

    public static void addCategory(IncomeCategory category) {
        incomeCategories.add(category);
    }
    public static void addCategory(ExpenseCategory category) {
        expenseCategories.add(category);
    }
    public static void deleteCategory(ExpenseCategory expenseCategory){
        expenseCategories.remove(expenseCategory);
    }
    public static void deleteCategory(IncomeCategory incomeCategory){
        incomeCategories.remove(incomeCategory);
    }

    public static List<Category> getIncomeCategories() {
        return incomeCategories;
    }
    public static List<Category> getExpenseCategories() {
        return expenseCategories;
    }

    public static void updateExpenseCategory(ExpenseCategory expenseCategory, String name) {
        expenseCategory.setName(name);
    }
    public static void updateExpenseCategory(ExpenseCategory expenseCategory, YearMonth yearMonth, BigDecimal budget) {
        expenseCategory.setBudgetLimit(yearMonth,budget);
    }

    public static void updateIncomeCategory(IncomeCategory incomeCategory, String name) {
        incomeCategory.setName(name);
    }
}
