package service;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;
import repository.CategoryRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class ExpenseCategoryService{


    public static  List<Category> getCategories() {
        return CategoryRepository.getExpenseCategories();
    }

    public static int addCategory(String name, BigDecimal budget, YearMonth yearMonth) {
        return CategoryRepository.addCategory(new ExpenseCategory(name,0,budget,yearMonth));
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
