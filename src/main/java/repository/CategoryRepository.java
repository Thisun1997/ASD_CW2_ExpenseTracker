package repository;

import model.Category;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static final List<Category> categories = new ArrayList<>();
    private static final List<Category> categoryBudgetForMonths = new ArrayList<>();

    public static int addCategory(Category category) {
        category.setCategoryId(IdGenerator.getNextCategoryId());
        categories.add(category);
        return category.getCategoryId();
    }

    public static Category getCategory(int categoryId) {
        for (Category category: categories) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    public static List<Category> getAllCategories() {
        return categories;
    }

    public static void deleteCategory(int categoryId) {
        Category categoryToRemove = null;

        // Find the category to remove
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                categoryToRemove = category;
                break;
            }
        }

        // If category found, remove it
        if (categoryToRemove != null) {
            categories.remove(categoryToRemove);

            // Remove associated budget categories
            List<Category> budgetCategoriesToRemove = new ArrayList<>();
            for (Category budgetCategory : categoryBudgetForMonths) {
                if (budgetCategory.getCategoryId() == categoryId) {
                    budgetCategoriesToRemove.add(budgetCategory);
                }
            }
            categoryBudgetForMonths.removeAll(budgetCategoriesToRemove);
        }
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
