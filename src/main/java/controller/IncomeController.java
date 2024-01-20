package controller;

import model.Category;
import service.IncomeCategoryService;

import java.time.YearMonth;
import java.util.Scanner;

public class IncomeController extends Controller{
    private static IncomeController incomeController= null;

    private IncomeController(Scanner scanner, YearMonth yearMonth){
        super(scanner,yearMonth);
    }
    public static Controller getInstance(Scanner scanner,YearMonth yearMonth){
        if(incomeController == null){
            incomeController = new IncomeController(scanner,yearMonth);
        }
        return incomeController;
    }

    @Override
    public void showCategories() {
        System.out.println("*************** Income Categories ***************");
        for(Category cat: IncomeCategoryService.getCategories()){
            System.out.println(cat.toString());
        }
    }

    @Override
    public int addCategory() {
        System.out.print("Please enter category name:");
        String name = scanner.nextLine();
        return IncomeCategoryService.addCategory(name);
    }


}
