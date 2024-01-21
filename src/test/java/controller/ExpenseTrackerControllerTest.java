//package controller;
//
//import model.BudgetLineItem;
//import model.Category;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import service.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.times;
//
//@Order(4)
//class ControllerTest {
//
//    static BudgetLineItem budgetLineItem = new BudgetLineItem();
//    static Category category = new Category.CategoryBuilder("test_cat_3",0).build();
//    static List<BudgetLineItem> budgetLineItems = new ArrayList<>();
//    static List<Category> categories = new ArrayList<>();
//
//    @BeforeAll
//    static void init() {
//        categories.add(category);
//        budgetLineItems.add(budgetLineItem);
//    }
//
//
//    @Test
//    void addExpense() {
//        try (MockedStatic<BudgetService> progressCheckServiceMockedStatic = Mockito.mockStatic(BudgetService.class)){
//            ExpenseTrackerController.addExpense(10,0,1);
//            progressCheckServiceMockedStatic.verify(() -> BudgetService.addExpense(10,0,1), times(1));
//        }
//    }
//
//    @Test
//    void addCategory() {
//        try (MockedStatic<CategoryService> categoryServiceMockedStatic = Mockito.mockStatic(CategoryService.class)){
//            categoryServiceMockedStatic.when(() -> CategoryService.addCategory("test_cat_3")).thenReturn(0);
//            ExpenseTrackerController.addCategory("test_cat_3");
//            categoryServiceMockedStatic.verify(() -> CategoryService.addCategory("test_cat_3"), times(1));
//        }
//    }
//
//    @Test
//    void getProgress() {
//        try (MockedStatic<BudgetService> progressCheckServiceMockedStatic = Mockito.mockStatic(BudgetService.class)){
//            progressCheckServiceMockedStatic.when(() -> BudgetService.getProgress(1)).thenReturn(budgetLineItems);
//            ExpenseTrackerController.getProgress(1);
//            progressCheckServiceMockedStatic.verify(() -> BudgetService.getProgress(1), times(1));
//            assertEquals(1, BudgetService.getProgress(1).size());
//        }
//    }
//
//    @Test
//    void getCategories() {
//        try (MockedStatic<CategoryService> categoryServiceMockedStatic = Mockito.mockStatic(CategoryService.class)){
//            categoryServiceMockedStatic.when(()->CategoryService.getCategories(1)).thenReturn(categories);
//            ExpenseTrackerController.getCategories(1);
//            categoryServiceMockedStatic.verify(()->CategoryService.getCategories(1), times(1));
//        }
//    }
//
//    @Test
//    void addCategoryBudget() {
//        try (MockedStatic<BudgetService> progressCheckServiceMockedStatic = Mockito.mockStatic(BudgetService.class)){
//            progressCheckServiceMockedStatic.when(() -> BudgetService.addCategoryBudget(0,1,100)).thenReturn("added");
//            ExpenseTrackerController.addCategoryBudget(0,1,100);
//            progressCheckServiceMockedStatic.verify(() -> BudgetService.addCategoryBudget(0,1,100), times(1));
//        }
//    }
//}