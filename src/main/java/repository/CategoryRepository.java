package repository;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static final List<Category> categories = new ArrayList<>();

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

    public static List<Category> getCategories() {
        return categories;
    }
}
