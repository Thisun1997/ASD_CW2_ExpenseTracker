package service;

import model.ExpenseCategory;
import model.IncomeCategory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IncomeCategoryServiceTest {

    static String name = "salary";
    static String updatedName = "part time salary";

    @Test
    @Order(1)
    void addCategory() {
        assertAll(() -> IncomeCategoryService.addCategory(name));
    }

    @Test
    @Order(2)
    void getCategories() {
        List<IncomeCategory> categories = IncomeCategoryService.getCategories();
        assertEquals(1,categories.size());
        assertEquals(name,categories.get(0).getName());
    }

    @Test
    @Order(3)
    void getCategoryCount() {
        assertEquals(1,IncomeCategoryService.getCategoryCount());
    }

    @Test
    @Order(4)
    void getCategory() {
        assertEquals(name,IncomeCategoryService.getCategory(0).getName());
    }

    @Test
    @Order(5)
    void updateIncomeCategory() {
        IncomeCategoryService.updateIncomeCategory(0, updatedName);
        List<IncomeCategory> categories = IncomeCategoryService.getCategories();
        assertEquals(updatedName,categories.get(0).getName());
    }

    @Test
    @Order(6)
    void deleteCategory() {
        IncomeCategoryService.deleteCategory(0);
        List<IncomeCategory> categories = IncomeCategoryService.getCategories();
        assertTrue(categories.isEmpty());
    }

}