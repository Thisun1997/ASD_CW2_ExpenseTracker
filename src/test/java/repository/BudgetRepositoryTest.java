package repository;//package repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BudgetRepositoryTest {

    BigDecimal amount = BigDecimal.valueOf(1000);
    YearMonth yearMonth1= YearMonth.of(2024,1);
    YearMonth yearMonth2= YearMonth.of(2024,2);

    @Test
    @Order(1)
    void addBudget() {
        assertNull(BudgetRepository.setBudget(amount, yearMonth1));
    }

    @Test
    @Order(2)
    void updateBudget() {
        assertEquals(amount, BudgetRepository.setBudget(amount.multiply(BigDecimal.valueOf(2)), yearMonth1));
    }

    @Test
    @Order(3)
    void isBudgetAdded_budget_exists() {
        assertTrue(BudgetRepository.isBudgetAdded(yearMonth1));
    }

    @Test
    @Order(3)
    void isBudgetAdded_budget_not_exists() {
        assertFalse(BudgetRepository.isBudgetAdded(yearMonth2));
    }

    @Test
    @Order(4)
    void getBudget_budget_exists() {
        assertEquals(amount.multiply(BigDecimal.valueOf(2)), BudgetRepository.getBudget(yearMonth1));
    }

    @Test
    @Order(4)
    void getBudget_budget_not_exists() {
        assertEquals(BigDecimal.valueOf(0), BudgetRepository.getBudget(yearMonth2));
    }


}