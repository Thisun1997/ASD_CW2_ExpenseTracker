package service;

import exception.CategoryException;
import exception.ExpenseTrackerException;
import model.Category;
import model.Expense;
import repository.CategoryRepository;
import repository.ExpenseRepository;

import java.util.List;

public class CategoryService {

    public static int addCategory(String name) {
        return CategoryRepository.addCategory(new Category.CategoryBuilder(name, 0).build());
    }

    public static List<Category> getCategories(int month) {
        return CategoryRepository.getCategories(month);
    }

    public static List<Category> getAllCategories() {
        return CategoryRepository.getAllCategories();
    }

    public static void editCategory(int categoryId, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new CategoryException.CategoryNameNotProvidedException("New name not provided");
        }

        Category category = CategoryRepository.getCategory(categoryId);
        if (category != null) {
            // Update the category name
            category.setName(newName);
        } else {
            throw new CategoryException.CategoryNotFoundException("Category not found");
        }
    }

    public static void deleteCategory(int categoryId) {
        Category categoryToDelete = CategoryRepository.getCategory(categoryId);
        if (categoryToDelete != null) {
            // Check if there are associated expenses
            List<Expense> expenses = ExpenseRepository.getTransactionsForCategory(categoryId);
            if (expenses.isEmpty()) {
                // Delete the category if no associated expenses
                CategoryRepository.deleteCategory(categoryId);
            } else {
                throw new CategoryException.CategoryHasExpensesException("Cannot delete category with associated expenses");
            }
        } else {
            throw new CategoryException.CategoryNotFoundException("Category not found");
        }
    }
}
