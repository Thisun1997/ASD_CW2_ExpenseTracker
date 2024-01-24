package repository;

import model.Category;
import model.ExpenseCategory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryTest {

    static YearMonth yearMonth = YearMonth.of(2024,1);
    static String expenseCategoryName = "test_category";
    static ExpenseCategory category = new ExpenseCategory(expenseCategoryName,0, BigDecimal.valueOf(100), yearMonth);

    @Test
    @Order(1)
    void addExpenseCategory() {
        assertEquals(0,CategoryRepository.addCategory(category));
    }

    @Test
    void getExpenseCategory_existing() {
        ExpenseCategory categoryFetched = (ExpenseCategory) CategoryRepository.getCategory(expenseCategoryName);
        assertEquals(expenseCategoryName,categoryFetched.getName());
        assertEquals(BigDecimal.valueOf(100),categoryFetched.getBudgetLimit(yearMonth));
    }

    @Test
    void getExpenseCategory_not_existing() {
        Category nonExistingCategory = CategoryRepository.getCategory("test_category_1");
        assertNull(nonExistingCategory);
    }

    @Test
    void getExpenseCategories() {
        List<Category> categories = CategoryRepository.getExpenseCategories();
        assertEquals(1,categories.size());
        assertEquals(expenseCategoryName,categories.get(0).getName());
        assertEquals(BigDecimal.valueOf(100),((ExpenseCategory)categories.get(0)).getBudgetLimit(yearMonth));
    }
}