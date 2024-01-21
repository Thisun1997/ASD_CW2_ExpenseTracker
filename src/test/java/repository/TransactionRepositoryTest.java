package repository;

import model.Category;
import model.Expense;
import model.ExpenseCategory;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
class TransactionRepositoryTest {

    static String expenseCategoryName = "test_category";
    static ExpenseCategory category = new ExpenseCategory(expenseCategoryName,0, BigDecimal.valueOf(100));
    static Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
    static List<Category> categories = Collections.singletonList(category);
    static YearMonth yearMonth= YearMonth.of(2024,1);

    @Test
    void addExpense() {
        assertAll(() -> TransactionRepository.addTransaction(yearMonth, expense));
    }

    @Nested
    class TestCasesAfterAddExpense {

        @Test
        void getExpenses() {
            List<Expense> expenses = TransactionRepository.getExpenses(yearMonth);
            assertEquals(1,expenses.size());
            assertEquals(expense,expenses.get(0));
        }

        @Test
        void getTransactionsGroupedByYearMonthAndCategory() {
            HashMap<String, List<Expense>> categoryMapFetched = TransactionRepository.getTransactionsGroupedByYearMonthAndCategory(categories, yearMonth);
            assertEquals(1,categoryMapFetched.keySet().size());
            assertEquals(expenseCategoryName,categoryMapFetched.keySet().stream().toList().get(0));
            assertEquals(1,categoryMapFetched.get(expenseCategoryName).size());
            assertEquals(expense,categoryMapFetched.get(expenseCategoryName).get(0));
        }

    }
}