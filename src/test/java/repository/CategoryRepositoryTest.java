package repository;

import model.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryTest {

    Category category = new Category.CategoryBuilder("test_category",0).build();

    @Test
    @Order(1)
    void addCategory() {
        assertEquals(0,CategoryRepository.addCategory(category));
    }

    @Test
    @Order(2)
    void addCategoryBudget() {
        String res = CategoryRepository.addCategoryBudget(0,1,100);
        assertEquals("added", res);
    }

    @Test
    void addCategoryBudget_not_existing() {
        String res = CategoryRepository.addCategoryBudget(1,1,100);
        assertEquals("Category doesn't exist", res);
    }

    @Test
    void updateCategoryBudget() {
        String res = CategoryRepository.addCategoryBudget(0,1,200);
        assertEquals("updated", res);
    }

    @Test
    void getCategory_existing() {
        Category categoryFetched = CategoryRepository.getCategory(0);
        assertEquals("test_category",categoryFetched.getName());
        assertEquals(0,categoryFetched.getBudgetLimit());
    }

    @Test
    void getCategory_not_existing() {
        Category nonExistingCategory = CategoryRepository.getCategory(1);
        assertNull(nonExistingCategory);
    }

    @Test
    void getBudgetCategory_with_month() {
        Category categoryFetched = CategoryRepository.getBudgetCategory(0,1);
        assertEquals("test_category",categoryFetched.getName());
        assertEquals(200,categoryFetched.getBudgetLimit());
    }

    @Test
    void getBudgetCategory_without_month() {
        Category categoryFetched = CategoryRepository.getBudgetCategory(0,2);
        assertEquals("test_category",categoryFetched.getName());
        assertEquals(0,categoryFetched.getBudgetLimit());
    }

    @Test
    void getCategories() {
        List<Category> categories = CategoryRepository.getCategories(1);
        assertEquals(1,categories.size());
        assertEquals("test_category",categories.get(0).getName());
        assertEquals(200,categories.get(0).getBudgetLimit());
    }
}