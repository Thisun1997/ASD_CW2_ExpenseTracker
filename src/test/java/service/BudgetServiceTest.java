package service;

import model.BudgetLineItem;
import model.Category;
import model.Expense;
import model.ExpenseCategory;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repository.BudgetRepository;
import repository.CategoryRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;

import static constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BudgetServiceTest {

    static String categoryName = "test_category";
    static YearMonth yearMonth = YearMonth.of(2024,1);
    static ExpenseCategory category = new ExpenseCategory(categoryName,yearMonth, BigDecimal.valueOf(100));
    static Expense expense = new Expense.ExpenseBuilder(BigDecimal.valueOf(10)).setCategory(category).build();
    static List<Expense> expenses = Collections.singletonList(expense);
    static List<ExpenseCategory> budgetCategories = Collections.singletonList(category);
    static HashMap<String, List<Expense>> categoryMap = new HashMap<>();
    static BigDecimal budgetAmount = BigDecimal.valueOf(1000);

    @BeforeAll
    static void init() {
        categoryMap.put(categoryName,expenses);
    }

    @Test
    void getProgress() {
        try (MockedStatic<TransactionRepository> expenseRepositoryMockedStatic = Mockito.mockStatic(TransactionRepository.class);
             MockedStatic<BudgetRepository> budgetRepositoryMockedStatic = Mockito.mockStatic(BudgetRepository.class);
             MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
            categoryRepositoryMockedStatic.when(CategoryRepository::getExpenseCategories).thenReturn(budgetCategories);
            expenseRepositoryMockedStatic.when(() -> TransactionRepository.getTransactionsGroupedByYearMonthAndCategory(budgetCategories,yearMonth)).thenReturn(categoryMap);
            budgetRepositoryMockedStatic.when(() -> BudgetRepository.getBudget(yearMonth)).thenReturn(budgetAmount);
            budgetRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(categoryName)).thenReturn(category);
            List<BudgetLineItem> budgetItemList = BudgetService.getProgress(yearMonth);
            assertEquals(2,budgetItemList.size());
            assertEquals("test_category",budgetItemList.get(0).getLineItemName());
            assertEquals(BigDecimal.valueOf(10),budgetItemList.get(0).getTotal());
            assertEquals(BigDecimal.valueOf(100),budgetItemList.get(0).getLimit());
            assertEquals(TOTAL,budgetItemList.get(1).getLineItemName());
            assertEquals(BigDecimal.valueOf(10),budgetItemList.get(1).getTotal());
            assertEquals(BigDecimal.valueOf(1000),budgetItemList.get(1).getLimit());
        }
    }

    @Test
    void addBudget() {
        try (MockedStatic<BudgetRepository> budgetRepositoryMockedStatic = Mockito.mockStatic(BudgetRepository.class)){
            budgetRepositoryMockedStatic.when(() -> BudgetRepository.setBudget(budgetAmount, yearMonth)).thenReturn(null);
            assertEquals(NEW_VALUE_ADDED, BudgetService.setBudget(budgetAmount, yearMonth, YES));
        }
    }

    @Test
    void updateBudget_allow() {
        try (MockedStatic<BudgetRepository> budgetRepositoryMockedStatic = Mockito.mockStatic(BudgetRepository.class)){
            budgetRepositoryMockedStatic.when(() -> BudgetRepository.setBudget(budgetAmount, yearMonth)).thenReturn(budgetAmount);
            assertEquals(UPDATE_EXISTING_VALUE, BudgetService.setBudget(budgetAmount, yearMonth, YES));
        }
    }

    @Test
    void updateBudget_abort() {
        assertEquals(ABORT_UPDATE_MESSAGE, BudgetService.setBudget(budgetAmount, yearMonth, "N"));
    }

    @Test
    void isBudgetAdded() {
        try (MockedStatic<BudgetRepository> budgetRepositoryMockedStatic = Mockito.mockStatic(BudgetRepository.class)){
            budgetRepositoryMockedStatic.when(() -> BudgetRepository.isBudgetAdded(yearMonth)).thenReturn(true);
            assertTrue(BudgetService.isBudgetAdded(yearMonth));
        }
    }
}