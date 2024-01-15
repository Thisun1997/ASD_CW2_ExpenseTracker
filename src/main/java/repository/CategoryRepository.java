package repository;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static final List<Category> categories = new ArrayList<>();
    private static final List<Category> categoryBudgetForMonths = new ArrayList<>();

    public static int addCategory(Category category) {
        category.setCategoryId(categories.size());
        categories.add(category);
        return categories.size()-1;
    }

    public static Category getCategory(int categoryId) {
        for (Category category: categories) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    public static Category getBudgetCategory(int categoryId, int month) {
        for (Category category: categoryBudgetForMonths) {
            if (category.getCategoryId() == categoryId && category.getMonth() == month) {
                return category;
            }
        }
        return getCategory(categoryId);
    }

    public static List<Category> getCategories(int month) {
        List<Category> updatedCategories = new ArrayList<>();
        for (Category category: categories) {
            boolean categoryExists = false;
            for (Category budgetCategory: categoryBudgetForMonths) {
                if (budgetCategory.getCategoryId() == category.getCategoryId() && budgetCategory.getMonth() == month) {
                    categoryExists = true;
                    updatedCategories.add(budgetCategory);
                }
            }
            if (!categoryExists) {
                updatedCategories.add(category);
            }
        }
        return updatedCategories;
    }

    public static String addCategoryBudget(int categoryId, int month, double budgetLimit) {
        Category category = getCategory(categoryId);
        boolean update = false;
        if (category == null) {
            return "Category doesn't exist";
        }
        for (Category budgetCategory: categoryBudgetForMonths) {
            if (budgetCategory.getCategoryId() == category.getCategoryId() && budgetCategory.getMonth() == month) {
                categoryBudgetForMonths.remove(budgetCategory);
                category = budgetCategory;
                update = true;
                break;
            }
        }
        categoryBudgetForMonths.add(new Category.CategoryBuilder(category.getName(), categoryId)
                .setBudgetLimit(budgetLimit)
                .setMonth(month)
                .build());
        return update ? "updated" : "added";
    }
}
