package service;

import model.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(5)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {
    @Test
    @Order(1)
    void addCategory() {
        assertEquals(0,CategoryService.addCategory("test_cat_1"));
        assertEquals(1,CategoryService.addCategory("test_cat_2"));
    }
    @Test
    @Order(2)
    void getCategories() {
        List<Category> categories = CategoryService.getCategories(1);
        assertEquals(2,categories.size());
        assertEquals("test_cat_1",categories.get(0).getName());
        assertEquals(0,categories.get(0).getCategoryId());
        assertEquals(0.0,categories.get(0).getBudgetLimit());
    }
}
