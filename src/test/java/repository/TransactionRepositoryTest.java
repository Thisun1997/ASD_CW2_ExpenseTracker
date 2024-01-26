package repository;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(3)
class TransactionRepositoryTest {

    @BeforeEach
    void setUp() {
        // Clear transactionsMap before each test
        TransactionRepository.getTransactionsMap().clear();
    }

    @Test
    void addTransaction() {
        TransactionRepository.addTransaction(YearMonth.of(2022, 1), new Expense.ExpenseBuilder(BigDecimal.TEN).setCategory(new ExpenseCategory("food",  YearMonth.of(2022, 2), BigDecimal.TEN)).setDate("23").setNote("").setIsRecurring(false).setRecurringId(null).build());
        List<Transaction> transactions = TransactionRepository.getTransactions(YearMonth.of(2022, 1));

        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
    }

    @Test
    void getTransactions() {
        TransactionRepository.addTransaction(YearMonth.of(2022, 2), new Income("2022-02-15", BigDecimal.valueOf(500), "Salary", false, new IncomeCategory("Salary"), null));
        List<Transaction> transactions = TransactionRepository.getTransactions(YearMonth.of(2022, 2));

        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        assertTrue(transactions.get(0) instanceof Income);
        assertEquals("Salary", transactions.get(0).getNote());
    }

    @Test
    void getTransactionById() {
        Transaction income = new Income("2022-03-20", BigDecimal.valueOf(300), "Part-time job", false, new IncomeCategory("Part-time"), null);
        TransactionRepository.addTransaction(YearMonth.of(2022, 3), income);

        Transaction retrievedTransaction = TransactionRepository.getTransactionById(YearMonth.of(2022, 3), Integer.parseInt(income.getId()));

        assertNotNull(retrievedTransaction);
        assertEquals(income, retrievedTransaction);
    }

    @Test
    void deleteTransaction() {
        Transaction expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(50)).build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 4), expense);

        TransactionRepository.deleteTransaction(YearMonth.of(2022, 4), Integer.parseInt(expense.getId()));
        List<Transaction> transactions = TransactionRepository.getTransactions(YearMonth.of(2022, 4));

        assertTrue(transactions.isEmpty());
    }

    @Test
    void deleteRecurringTransaction() {
        Transaction recurringExpense = new Expense.ExpenseBuilder(BigDecimal.valueOf(20)).setIsRecurring(true).setRecurringId("123").build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 5), recurringExpense);

        List<Transaction> currentTransactions = TransactionRepository.getTransactions(YearMonth.of(2022, 5));
        List<Transaction> nextTransactions = TransactionRepository.getTransactions(YearMonth.of(2022, 6));

        assertNotNull(currentTransactions);
        assertNotNull(nextTransactions);

        TransactionRepository.deleteRecurringTransaction(YearMonth.of(2022, 5), "123");
        List<Transaction> currentTransactions1 = TransactionRepository.getTransactions(YearMonth.of(2022, 5));
        List<Transaction> nextTransactions1 = TransactionRepository.getTransactions(YearMonth.of(2022, 6));

        assertNotNull(currentTransactions1);
        assertTrue(nextTransactions1.isEmpty());
    }

    @Test
    void updateRecurringTransaction() {
        Transaction originalTransaction = new Income("2022-06-10", BigDecimal.valueOf(200), "Freelance work", true, new IncomeCategory("Freelance"), "456");

        TransactionRepository.updateRecurringTransaction(originalTransaction, new IncomeCategory("Updated Freelance"), BigDecimal.valueOf(250), "Updated work", "2022-06-10", false);

        assertEquals("Updated work", originalTransaction.getNote());
        assertEquals(BigDecimal.valueOf(250), originalTransaction.getAmount());
    }

    @Test
    void getExpenses() {
        TransactionRepository.addTransaction(YearMonth.of(2022, 2), new Income("2022-02-15", BigDecimal.valueOf(500), "Salary", false, new IncomeCategory("Salary"), null));
        TransactionRepository.addTransaction(YearMonth.of(2022, 2), new Expense.ExpenseBuilder(BigDecimal.valueOf(600)).build());
        List<Expense> expenses = TransactionRepository.getExpenses(YearMonth.of(2022, 2));

        assertFalse(expenses.isEmpty());
        assertEquals(1, expenses.size());
        assertEquals(expenses.get(0).getAmount(), BigDecimal.valueOf(600));
    }

    @Test
    void getTransactionsGroupedByYearMonthAndCategory() {
        String categoryName = "Groceries";
        ExpenseCategory category = new ExpenseCategory(categoryName,YearMonth.of(2022, 2), BigDecimal.valueOf(100));
        List<ExpenseCategory> categories = Collections.singletonList(category);
        Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 2), expense);
        HashMap<String, List<Expense>> categoryMapFetched = TransactionRepository.getTransactionsGroupedByYearMonthAndCategory(categories, YearMonth.of(2022, 2));

        assertEquals(1,categoryMapFetched.keySet().size());
        assertEquals(categoryName,categoryMapFetched.keySet().stream().toList().get(0));
        assertEquals(1,categoryMapFetched.get(categoryName).size());
        assertEquals(expense,categoryMapFetched.get(categoryName).get(0));
    }

    @Test
    void getTransactionCount_income_category() {
        IncomeCategory category = new IncomeCategory("Salary");
        Income income = new Income("2022-06-10", BigDecimal.valueOf(200), "Freelance work", true, category, "456");
        TransactionRepository.addTransaction(YearMonth.of(2022, 6), income);
        assertEquals(1,TransactionRepository.getTransactionCount(category));
    }

    @Test
    void getTransactionCount_expense_category() {
        ExpenseCategory category = new ExpenseCategory("food",YearMonth.of(2022, 6), BigDecimal.valueOf(100));
        Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 6), expense);
        assertEquals(1,TransactionRepository.getTransactionCount(category));
    }

    @Test
    void removeTransaction_income_category() {
        IncomeCategory category = new IncomeCategory("Salary");
        Income income = new Income("2022-06-10", BigDecimal.valueOf(200), "Freelance work", true, category, "456");
        TransactionRepository.addTransaction(YearMonth.of(2022, 6), income);
        assertAll(() -> TransactionRepository.removeTransactions(category));
        List<Transaction> transactions = TransactionRepository.getTransactions(YearMonth.of(2022, 6));
        assertTrue(transactions.isEmpty());
    }

    @Test
    void removeTransaction_expense_category() {
        ExpenseCategory category = new ExpenseCategory("food",YearMonth.of(2022, 6), BigDecimal.valueOf(100));
        Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 6), expense);
        assertAll(() -> TransactionRepository.removeTransactions(category));
        List<Transaction> transactions = TransactionRepository.getTransactions(YearMonth.of(2022, 6));
        assertTrue(transactions.isEmpty());
    }

    @Test
    void getTotalTransactionCount() {
        ExpenseCategory category = new ExpenseCategory("food",YearMonth.of(2022, 11), BigDecimal.valueOf(100));
        Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
        TransactionRepository.addTransaction(YearMonth.of(2022, 11), expense);
        assertEquals(4,TransactionRepository.getTotalTransactionCount(YearMonth.of(2022, 11)));
    }
}

