package repository;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static final List<Category> incomeCategories = new ArrayList<>();
    private static final List<Category> expenseCategories = new ArrayList<>();

    public static int addCategory(IncomeCategory category) {
        category.setCategoryId(incomeCategories.size());
        incomeCategories.add(category);
        return incomeCategories.size()-1;
    }
    public static int addCategory(ExpenseCategory category) {
        category.setCategoryId(expenseCategories.size());
        expenseCategories.add(category);
        return expenseCategories.size()-1;
    }

    public static Category getCategory(String categoryName) {
        for (Category category: expenseCategories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public static List<Category> getIncomeCategories() {
        return incomeCategories;
    }
    public static List<Category> getExpenseCategories() {
        return expenseCategories;
    }
}
