package controller;

import model.BudgetLineItem;
import model.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repository.CategoryRepository;
import repository.TransactionRepository;
import service.ProgressCheckService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Order(4)
class ExpenseTrackerControllerTest {

    static BudgetLineItem budgetLineItem = new BudgetLineItem();
    static Category category = new Category("test_cat_3",0,400);
    static List<BudgetLineItem> budgetLineItems = new ArrayList<>();
    static List<Category> categories = new ArrayList<>();

    @BeforeAll
    static void init() {
        categories.add(category);
        budgetLineItems.add(budgetLineItem);
    }


    @Test
    void addExpense() {
        try (MockedStatic<ProgressCheckService> progressCheckServiceMockedStatic = Mockito.mockStatic(ProgressCheckService.class)){
            ExpenseTrackerController.addExpense(10,0);
            progressCheckServiceMockedStatic.verify(() -> ProgressCheckService.addExpense(10,0), times(1));
        }
    }

    @Test
    void addCategory() {
        try (MockedStatic<ProgressCheckService> progressCheckServiceMockedStatic = Mockito.mockStatic(ProgressCheckService.class)){
            progressCheckServiceMockedStatic.when(() -> ProgressCheckService.addCategory("test_cat_3",400)).thenReturn(0);
            ExpenseTrackerController.addCategory("test_cat_3",400);
            progressCheckServiceMockedStatic.verify(() -> ProgressCheckService.addCategory("test_cat_3",400), times(1));
        }
    }

    @Test
    void getProgress() {
        try (MockedStatic<ProgressCheckService> progressCheckServiceMockedStatic = Mockito.mockStatic(ProgressCheckService.class)){
            progressCheckServiceMockedStatic.when(ProgressCheckService::getProgress).thenReturn(budgetLineItems);
            ExpenseTrackerController.getProgress();
            progressCheckServiceMockedStatic.verify(ProgressCheckService::getProgress, times(1));
            assertEquals(1,ProgressCheckService.getProgress().size());
        }
    }

    @Test
    void getCategories() {
        try (MockedStatic<ProgressCheckService> progressCheckServiceMockedStatic = Mockito.mockStatic(ProgressCheckService.class)){
            progressCheckServiceMockedStatic.when(ProgressCheckService::getCategories).thenReturn(categories);
            ExpenseTrackerController.getCategories();
            progressCheckServiceMockedStatic.verify(ProgressCheckService::getCategories, times(1));
        }
    }
}