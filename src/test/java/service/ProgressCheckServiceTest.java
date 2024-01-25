//package service;
//
//import model.BudgetLineItem;
//import model.Category;
//import model.Expense;
//import org.junit.jupiter.api.*;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import repository.CategoryRepository;
//import repository.ExpenseRepository;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Order(3)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ProgressCheckServiceTest {
//
//    static Category category = new Category.CategoryBuilder("test_category",0).setMonth(1).setBudgetLimit(100).build();
//    static Expense expense = new Expense.ExpenseBuilder(10).setCategory(category).build();
//    static List<Expense> expenses = Collections.singletonList(expense);
//    static List<Category> budgetCategories = Collections.singletonList(category);
//    static HashMap<Integer, List<Expense>> categoryMap = new HashMap<>();
//
//    @BeforeAll
//    static void init() {
//        categoryMap.put(0,expenses);
//    }
//
//    @Test
//    void getProgress() {
//        try (MockedStatic<ExpenseRepository> expenseRepositoryMockedStatic = Mockito.mockStatic(ExpenseRepository.class);
//             MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
//            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategories(1)).thenReturn(budgetCategories);
//            expenseRepositoryMockedStatic.when(() -> ExpenseRepository.getTransactionsGroupedByCategory(budgetCategories,1)).thenReturn(categoryMap);
//            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getBudgetCategory(0,1)).thenReturn(category);
//            List<BudgetLineItem> budgetItemList = ProgressCheckService.getProgress(1);
//            assertEquals(2,budgetItemList.size());
//            assertEquals("test_category",budgetItemList.get(0).getLineItemName());
//            assertEquals(10,budgetItemList.get(0).getTotal());
//            assertEquals(100,budgetItemList.get(0).getLimit());
//            assertEquals("Total",budgetItemList.get(1).getLineItemName());
//            assertEquals(10,budgetItemList.get(1).getTotal());
//            assertEquals(1000,budgetItemList.get(1).getLimit());
//        }
//    }
//
//    @Test
//    void addExpense_category_exists() {
//        try (MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
//            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(0)).thenReturn(category);
//            assertEquals("Expense added successfully",ProgressCheckService.addExpense(10,0,1));
//        }
//    }
//
//    @Test
//    void addExpense_category_not_exists() {
//        try (MockedStatic<CategoryRepository> categoryRepositoryMockedStatic = Mockito.mockStatic(CategoryRepository.class)){
//            categoryRepositoryMockedStatic.when(() -> CategoryRepository.getCategory(1)).thenReturn(null);
//            assertEquals("Category doesn't exist",ProgressCheckService.addExpense(10,1,1));
//        }
//    }
//
//    @Test
//    @Order(1)
//    void addCategoryBudget_add() {
//        CategoryService.addCategory("test_cat_1");
//        assertEquals("added",ProgressCheckService.addCategoryBudget(1,1,100));
//    }
//
//    @Test
//    @Order(2)
//    void addCategoryBudget_update() {
//        assertEquals("updated",ProgressCheckService.addCategoryBudget(0,1,200));
//    }
//
//    @Test
//    @Order(3)
//    void addCategoryBudget_category_not_exist() {
//        assertEquals("Category doesn't exist",ProgressCheckService.addCategoryBudget(2,1,100));
//    }
//}
