package service;

import model.Category;
import org.junit.jupiter.api.*;
import repository.CategoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(5)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {
    @Test
    @Order(1)
    void addCategory() {
        assertEquals(2,CategoryService.addCategory("test_cat_1"));
        assertEquals(3,CategoryService.addCategory("test_cat_2"));
    }
    @Test
    @Order(2)
    void getCategories() {
        List<Category> categories = CategoryService.getCategories(1);
        assertEquals(4,categories.size());
        assertEquals("test_category",categories.get(0).getName());
        assertEquals(0,categories.get(0).getCategoryId());
        assertEquals(200.0,categories.get(0).getBudgetLimit());
    }
}
