package repository;

import model.Category;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
class CategoryRepositoryTest {

    Category category = new Category("test_category",0,100);

    @Test
    void addCategory() {
        assertEquals(0,CategoryRepository.addCategory(category));
    }

    @Test
    void getCategory_existing() {
        Category categoryFetched = CategoryRepository.getCategory(0);
        assertEquals("test_category",categoryFetched.getName());
        assertEquals(100,categoryFetched.getBudgetLimit());
    }

    @Test
    void getCategory_not_existing() {
        Category nonExistingCategory = CategoryRepository.getCategory(1);
        assertNull(nonExistingCategory);
    }

    @Test
    void getCategories() {
        List<Category> categories = CategoryRepository.getCategories();
        assertEquals(1,categories.size());
        assertEquals("test_category",categories.get(0).getName());
        assertEquals(100,categories.get(0).getBudgetLimit());
    }
}