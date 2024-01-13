package repository;

import model.Category;
import model.Expense;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
class TransactionRepositoryTest {

    static Category category = new Category("test_category",0,100);
    static Expense expense = new Expense.ExpenseBuilder(10).setCategory(category).build();
    List<Category> categories = Collections.singletonList(category);

    @Test
    void addExpense() {
        assertAll(() -> TransactionRepository.addExpense(expense));
    }

    @Nested
    class TestCasesAfterAddExpense {

        @Test
        void getTransactions() {
            List<Expense> expenses = TransactionRepository.getTransactions();
            assertEquals(1,expenses.size());
            assertEquals(expense,expenses.get(0));
        }

        @Test
        void getTransactionsGroupedByCategory() {
            HashMap<Integer, List<Expense>> categoryMapFetched = TransactionRepository.getTransactionsGroupedByCategory(categories);
            assertEquals(1,categoryMapFetched.keySet().size());
            assertEquals(0,categoryMapFetched.keySet().stream().toList().get(0));
            assertEquals(1,categoryMapFetched.get(0).size());
            assertEquals(expense,categoryMapFetched.get(0).get(0));
        }

    }
}