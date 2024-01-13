package service;

import model.BudgetLineItem;
import model.Category;
import model.Expense;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repository.CategoryRepository;
import repository.TransactionRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Order(3)
class ProgressCheckServiceTest {

    static Category category = new Category("test_category",1,100);
    static Expense expense = new Expense.ExpenseBuilder(10).setCategory(category).build();
    static List<Expense> expenses = Collections.singletonList(expense);
    static HashMap<Integer, List<Expense>> categoryMap = new HashMap<>();

    @BeforeAll
    static void init() {
        categoryMap.put(0,expenses);
    }

    @Test
    void getProgress() {
        try (MockedStatic<TransactionRepository> transactionRepositoryMockedStatic = Mockito.mockStatic(TransactionRepository.class);
         MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
            transactionRepositoryMockedStatic.when(TransactionRepository::getTransactionsGroupedByCategory).thenReturn(categoryMap);
            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(0)).thenReturn(category);
            List<BudgetLineItem> budgetItemList = ProgressCheckService.getProgress();
            assertEquals(2,budgetItemList.size());
            assertEquals("test_category",budgetItemList.get(0).getLineItemName());
            assertEquals(10,budgetItemList.get(0).getTotal());
            assertEquals(100,budgetItemList.get(0).getLimit());
            assertEquals("Total",budgetItemList.get(1).getLineItemName());
            assertEquals(10,budgetItemList.get(1).getTotal());
            assertEquals(1000,budgetItemList.get(1).getLimit());
        }
    }

    @Test
    void addExpense_category_exists() {
        try (MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(0)).thenReturn(category);
            assertEquals("Expense added successfully",ProgressCheckService.addExpense(10,0));
        }
    }

    @Test
    void addExpense_category_not_exists() {
        try (MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(1)).thenReturn(null);
            assertEquals("Category doesn't exist",ProgressCheckService.addExpense(10,1));
        }
    }

    @Test
    void addCategory() {
        assertEquals(1,ProgressCheckService.addCategory("test_cat_2",200));
    }

    @Test
    void getCategories() {
        List<Category> categories = ProgressCheckService.getCategories();
        assertEquals(2,categories.size());
        assertEquals("test_category",categories.get(0).getName());
        assertEquals(0,categories.get(0).getCategoryId());
        assertEquals(100,categories.get(0).getBudgetLimit());
    }
}