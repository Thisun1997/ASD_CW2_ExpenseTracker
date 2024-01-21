package service;

import model.Category;
import model.BudgetLineItem;
import model.Expense;
import model.ExpenseCategory;
import repository.BudgetRepository;
import repository.CategoryRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static constants.Constants.*;

public class BudgetService {
    public static List<BudgetLineItem> getProgress(YearMonth yearMonth) {
        List<BudgetLineItem> lineItems = new ArrayList<>();
        List<Category> categories = CategoryRepository.getExpenseCategories();
        HashMap<String, List<Expense>> categoryMap = TransactionRepository.getTransactionsGroupedByYearMonthAndCategory(categories, yearMonth);
        BudgetLineItem totalBudgetLineItem = new BudgetLineItem();
        totalBudgetLineItem.setLineItemName(TOTAL);
        totalBudgetLineItem.setLimit(BudgetRepository.getBudget(yearMonth));
        for (String categoryName: categoryMap.keySet()) {
            ExpenseCategory category = (ExpenseCategory) CategoryRepository.getCategory(categoryName);
            BigDecimal budgetLimit = category.getBudgetLimit(yearMonth);
            BudgetLineItem budgetLineItem = new BudgetLineItem();
            budgetLineItem.setLineItemName(categoryName);
            budgetLineItem.setLimit(budgetLimit);
            for (Expense expense: categoryMap.get(categoryName)){
                budgetLineItem.addExpense(expense.getAmount());
            }
            totalBudgetLineItem.addComponent(budgetLineItem);
            lineItems.add(budgetLineItem);
        }
        lineItems.add(totalBudgetLineItem);
        return lineItems;
    }

    public static String setBudget(BigDecimal budget, YearMonth yearMonth, String action) {
        return action.equals(YES) ? ((BudgetRepository.setBudget(budget, yearMonth) == null) ? NEW_VALUE_ADDED : UPDATE_EXISTING_VALUE) : ABORT_UPDATE_MESSAGE ;
    }

    public static boolean isBudgetAdded(YearMonth yearMonth) {
        return BudgetRepository.isBudgetAdded(yearMonth);
    }
}
