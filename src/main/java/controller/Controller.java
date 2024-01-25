package controller;

import java.time.YearMonth;
import java.util.Scanner;

public abstract class Controller {
    protected Scanner scanner;
    protected YearMonth yearMonth;

    public Controller(Scanner scanner, YearMonth yearMonth){
        this.scanner = scanner;
        this.yearMonth = yearMonth;
    }
    public abstract void showCategories();
    public abstract String addCategory();
    public abstract void deleteCategory();
    public abstract void updateCategory();
}
