package service;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;
import repository.CategoryRepository;

import java.util.List;
import java.util.Scanner;

public class IncomeCategoryService{

    public static List<Category> getCategories() {
        return CategoryRepository.getIncomeCategories();
    }

    public static int addCategory(String name) {
        return CategoryRepository.addCategory(new IncomeCategory(name,0));
    }
}
