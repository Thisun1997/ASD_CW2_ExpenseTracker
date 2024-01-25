package service;

import model.Category;
import model.ExpenseCategory;
import model.IncomeCategory;
import repository.CategoryRepository;

import java.util.List;
import java.util.Scanner;

public class IncomeCategoryService{
    private static List<Category> incomeCategories;

    public static List<Category> getCategories() {
        incomeCategories = CategoryRepository.getIncomeCategories();
        return incomeCategories;
    }

    public static void addCategory(String name) {
         CategoryRepository.addCategory(new IncomeCategory(name));
    }

    public static int getCategoryCount(){
        return incomeCategories.size();
    }

    public static Category getCategory(int index) { return incomeCategories.get(index);}

    public static void deleteCategory(int index){
        CategoryRepository.deleteCategory((IncomeCategory) incomeCategories.get(index));
        incomeCategories = CategoryRepository.getIncomeCategories();
    }

    public static void updateIncomeCategory(int index, String name) {
        CategoryRepository.updateIncomeCategory((IncomeCategory) incomeCategories.get(index),name);
        incomeCategories = CategoryRepository.getIncomeCategories();
    }
}
