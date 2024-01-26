package controller;

import model.ExpenseCategory;
import model.IncomeCategory;
import repository.CategoryRepository;

public class CategoryInitializer {
    public static void initialize(){
        CategoryRepository.addCategory(new IncomeCategory("Salary"));
        CategoryRepository.addCategory(new IncomeCategory("Other"));

        CategoryRepository.addCategory(new ExpenseCategory("Fuel"));
        CategoryRepository.addCategory(new ExpenseCategory("Holidays"));
        CategoryRepository.addCategory(new ExpenseCategory("Shopping"));
        CategoryRepository.addCategory(new ExpenseCategory("Cloths"));
        CategoryRepository.addCategory(new ExpenseCategory("Eating Out"));
        CategoryRepository.addCategory(new ExpenseCategory("Entertainment"));
        CategoryRepository.addCategory(new ExpenseCategory("General"));
        CategoryRepository.addCategory(new ExpenseCategory("Gifts"));
        CategoryRepository.addCategory(new ExpenseCategory("Kids"));
        CategoryRepository.addCategory(new ExpenseCategory("Sports"));
        CategoryRepository.addCategory(new ExpenseCategory("Travel"));
        CategoryRepository.addCategory(new ExpenseCategory("Other"));

    }
}
