package service;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;
import repository.CategoryRepository;

import java.util.List;
import java.util.Scanner;

public class IncomeCategoryService{
    private static List<IncomeCategory> incomeCategories;

    public static List<IncomeCategory> getCategories() {
        incomeCategories = CategoryRepository.getIncomeCategories();
        return incomeCategories;
    }

    public static void addCategory(String name) {
         CategoryRepository.addCategory(new IncomeCategory(name));
    }

    public static int getCategoryCount(){
        return incomeCategories.size();
    }

    public static IncomeCategory getCategory(int index) { return incomeCategories.get(index);}

    public static void deleteCategory(int index){
        CategoryRepository.deleteCategory(incomeCategories.get(index));
        incomeCategories = CategoryRepository.getIncomeCategories();
    }

    public static void updateIncomeCategory(int index, String name) {
        CategoryRepository.updateIncomeCategory(incomeCategories.get(index),name);
        incomeCategories = CategoryRepository.getIncomeCategories();
    }
}
