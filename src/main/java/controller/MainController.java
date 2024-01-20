package controller;

import java.time.YearMonth;
import java.util.Scanner;

public class MainController {

    private static Scanner scanner;
    private static Controller controller;
    private static YearMonth yearMonth;

    private static void initialize(){
        yearMonth = YearMonth.now();
        scanner = new Scanner(System.in);
    }
    private static void initializeController(int choice){
        if(choice == 1){
            controller = IncomeController.getInstance(scanner, yearMonth);
        }else {
            controller = ExpenseController.getInstance(scanner, yearMonth);
        }
    }
    public static void execute(){
        initialize();
        System.out.println("Welcome to the Expense Tracking App! \n");

        while (true) {
            printSeparator();
            System.out.println("Year :"+yearMonth.getYear()+" Month: "+yearMonth.getMonth());
            System.out.println("Following actions are possible: \n" +
                    "1. Show categories \n" +
                    "2. Show transactions \n" +
                    "3. Show Budget \n" +
                    "4. Change the current month and year \n" +
                    "5. Exit");

            System.out.println();

            int choice = getCommand(scanner);

            switch (choice) {
                case 1 -> {
                    handleCategories();
                }
                case 2 -> {
                    handleTransactions();
                }
                case 3 -> {
                    handleProgress();
                }
                case 4 -> {
                    changeYearMonth();
                }
                case 5 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    // Closing the scanner
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void handleCategories(){
        int subChoice;
        System.out.println("*****Categories*****");
        System.out.println("Category Types\n1:Income\n2:Expense");
        subChoice = getCommand(scanner);
        printSeparator();
        initializeController(subChoice);
        boolean exitLoop = false;
        while(!exitLoop){
            printSeparator();
            showCategories();
            System.out.println("1: Add Category\t2: Delete Category\t3: Update Category\t4: Exit");
            subChoice = getCommand(scanner);
            switch (subChoice) {
                case 1 -> {
                    addCategory();
                }
                case 2 -> {
//                                ExpenseTrackerController.deletCategory(categoryService);
                }
                case 3 -> {
//                                ExpenseTrackerController.updateCategory(categoryService);
                }
                case 4 -> {
                    exitLoop = true;
                }
                default -> {
                    System.out.println("Invalid choice. Exiting to home.");
                    exitLoop = true;
                }

            }
        }
    }

    private static void handleTransactions(){

    }

    private static void handleProgress(){

    }

    private static void changeYearMonth(){

    }

    private static void showCategories() {
        controller.showCategories();
    }

    private static void addCategory() {
        System.out.println("Category added successfully with category Id: " + controller.addCategory());
    }
    public static int getCommand(Scanner scanner){
        int choice;
        while (true){
            try {
                System.out.print("Enter Command: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    public static void printSeparator(){
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
