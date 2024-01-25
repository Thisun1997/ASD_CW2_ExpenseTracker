package repository;

import model.Category;
import model.Transaction;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
class ExpenseRepositoryTest {

    static Category category = new Category.CategoryBuilder("test_category",0).build();
    static Transaction expense = new Transaction.ExpenseBuilder(10).setCategory(category).setMonth(1).build();
    List<Category> categories = Collections.singletonList(category);

    @Test
    void addExpense() {
        assertAll(() -> ExpenseRepository.addExpense(expense));
    }

    @Nested
    class TestCasesAfterAddExpense {

        @Test
        void getTransactions() {
            List<Transaction> expenses = ExpenseRepository.getTransactions(1);
            assertEquals(1,expenses.size());
            assertEquals(expense,expenses.get(0));
        }

        @Test
        void getTransactionsGroupedByCategory() {
            HashMap<Integer, List<Transaction>> categoryMapFetched = ExpenseRepository.getTransactionsGroupedByCategory(categories, 1);
            assertEquals(1,categoryMapFetched.keySet().size());
            assertEquals(0,categoryMapFetched.keySet().stream().toList().get(0));
            assertEquals(1,categoryMapFetched.get(0).size());
            assertEquals(expense,categoryMapFetched.get(0).get(0));
        }

    }
}