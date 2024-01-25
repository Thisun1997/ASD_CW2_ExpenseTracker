package service;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @BeforeEach
    void setUp() {
        // Clear transactionsMap before each test
        TransactionService.getTransactionsMap().clear();
    }

    @Test
    void addTransactionNonRecurring() {
        TransactionService.addTransaction(YearMonth.of(2022, 1), "2022-01-15", BigDecimal.valueOf(100), "Groceries", new ExpenseCategory("Food", 1, BigDecimal.TEN), false, true);
        List<Transaction> transactions = TransactionService.getTransactions(YearMonth.of(2022, 1));

        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        assertFalse(transactions.get(0).isRecurring());
        assertTrue(transactions.get(0) instanceof Expense);
        assertEquals("Groceries", transactions.get(0).getNote());
    }

    @Test
    void addTransactionRecurring() {
        TransactionService.addTransaction(YearMonth.of(2022, 2), "2022-02-20", BigDecimal.valueOf(200), "Monthly Subscription", new ExpenseCategory("Subscription", 2, BigDecimal.TEN), true, true);
        Map<YearMonth, List<Transaction>> transactions = TransactionService.getTransactionsMap();

        assertEquals(12, transactions.size()); // 12 months for a recurring transaction

        List<Transaction> transactionList = transactions.get(YearMonth.of(2022, 6));
        assertFalse(transactions.isEmpty());
        assertTrue(transactionList.get(0).isRecurring());
        assertTrue(transactionList.get(0) instanceof Expense);
        assertEquals("Monthly Subscription", transactionList.get(0).getNote());
    }

    @Test
    void deleteTransaction() {
        TransactionService.addTransaction(YearMonth.of(2022, 3), "2022-03-10", BigDecimal.valueOf(50), "Dinner", new ExpenseCategory("Dining", 3, BigDecimal.TEN), false, true);
        TransactionService.deleteTransaction(YearMonth.of(2022, 3), Integer.parseInt(TransactionRepository.getTransactions(YearMonth.of(2022, 3)).get(0).getId()));

        List<Transaction> transactions = TransactionService.getTransactions(YearMonth.of(2022, 3));
        assertTrue(transactions.isEmpty());
    }

    @Test
    void updateTransaction() {
        TransactionService.addTransaction(YearMonth.of(2022, 4), "2022-04-05", BigDecimal.valueOf(120), "Shopping", new ExpenseCategory("Retail", 4, BigDecimal.TEN), false, true);
        TransactionService.updateTransaction(YearMonth.of(2022, 4), Integer.parseInt(TransactionRepository.getTransactions(YearMonth.of(2022, 4)).get(0).getId()),
                new ExpenseCategory("Updated Retail", 5, BigDecimal.TEN), BigDecimal.valueOf(150), "Updated Shopping", "2022-04-05", true, true);

        Transaction updatedTransaction = TransactionService.getTransactionById(YearMonth.of(2022, 4), Integer.parseInt(TransactionRepository.getTransactions(YearMonth.of(2022, 4)).get(0).getId()));

        assertNotNull(updatedTransaction);
        assertEquals("Updated Retail", ((Expense) updatedTransaction).getCategory().getName());
        assertEquals(BigDecimal.valueOf(150), updatedTransaction.getAmount());
        assertEquals("Updated Shopping", updatedTransaction.getNote());
        assertTrue(updatedTransaction.isRecurring());
    }

    @Test
    void deleteRecurringTransaction() {
        TransactionService.addTransaction(YearMonth.of(2022, 5), "2022-05-15", BigDecimal.valueOf(80), "Gym Membership", new ExpenseCategory("Fitness", 6, BigDecimal.TEN), true, true);
        TransactionService.deleteRecurringTransaction(YearMonth.of(2022, 5), TransactionRepository.getTransactions(YearMonth.of(2022, 5)).get(0).getRecurringId());

        List<Transaction> transactions = TransactionService.getTransactions(YearMonth.of(2022, 5));
        assertFalse(transactions.isEmpty());

        List<Transaction> upcomingMonthTransactions = TransactionService.getTransactions(YearMonth.of(2022, 12));
        assertTrue(upcomingMonthTransactions.isEmpty());
    }
}
