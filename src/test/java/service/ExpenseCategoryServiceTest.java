package service;

import model.ExpenseCategory;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseCategoryServiceTest {

    static String name = "food";
    static String updatedName = "meat";
    static YearMonth yearMonth = YearMonth.of(2022,1);
    static BigDecimal amount = BigDecimal.valueOf(10);
    static BigDecimal updatedAmount = BigDecimal.valueOf(20);

    @Test
    @Order(1)
    void addCategory() {
        assertAll(() -> ExpenseCategoryService.addCategory(name,yearMonth,amount));
    }

    @Test
    @Order(2)
    void getCategories() {
        List<ExpenseCategory> categories = ExpenseCategoryService.getCategories();
        assertEquals(1,categories.size());
        assertEquals(name,categories.get(0).getName());
        assertEquals(amount,categories.get(0).getBudgetLimit(yearMonth));
    }

    @Test
    @Order(3)
    void getCategoryCount() {
        assertEquals(1,ExpenseCategoryService.getCategoryCount());
    }

    @Test
    @Order(4)
    void getCategory() {
        assertEquals(name,ExpenseCategoryService.getCategory(0).getName());
        assertEquals(amount,ExpenseCategoryService.getCategory(0).getBudgetLimit(yearMonth));
    }

    @Test
    @Order(5)
    void updateExpenseCategory_name() {
        ExpenseCategoryService.updateExpenseCategory(0, updatedName);
        List<ExpenseCategory> categories = ExpenseCategoryService.getCategories();
        assertEquals(updatedName,categories.get(0).getName());
        assertEquals(amount,categories.get(0).getBudgetLimit(yearMonth));
    }

    @Test
    @Order(6)
    void UpdateExpenseCategory_budget() {
        ExpenseCategoryService.updateExpenseCategory(0, yearMonth, updatedAmount);
        List<ExpenseCategory> categories = ExpenseCategoryService.getCategories();
        assertEquals(updatedName,categories.get(0).getName());
        assertEquals(updatedAmount,categories.get(0).getBudgetLimit(yearMonth));
    }

    @Test
    @Order(7)
    void deleteCategory() {
        ExpenseCategoryService.deleteCategory(0);
        List<ExpenseCategory> categories = ExpenseCategoryService.getCategories();
        assertTrue(categories.isEmpty());
    }

}