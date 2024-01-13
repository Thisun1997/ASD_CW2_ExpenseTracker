import controller.ExpenseTrackerController;

import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Expense Tracking App! \n" +
                "Following actions are possible: \n" +
                "1. Add category \n" +
                "2. Add expense \n" +
                "3. Check progress \n" +
                "4. Exit");

        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.print("-");
            }

            System.out.println();

            int choice;

            try {
                System.out.print("Enter Command: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("*****Add a category*****");
                    System.out.print("Enter Category Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Budget Limit: ");
                    double budgetLimit = Double.parseDouble(scanner.nextLine());
                    ExpenseTrackerController.addCategory(name,budgetLimit);
                }
                case 2 -> {
                    System.out.print("Enter Expense Amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    System.out.print("""
                            Enter Category Id from the available categories below\s
                             Category Id | Category Name | Budget Limit\s
                            """);
                    ExpenseTrackerController.getCategories();
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    ExpenseTrackerController.addExpense(amount, categoryId);
                }
                case 3 -> {
                    ExpenseTrackerController.getProgress();
                }
                case 4 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    // Closing the scanner
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }


//        System.out.println(ExpenseTrackerController.addExpense(10,1));
//        System.out.println(ExpenseTrackerController.addExpense(20,1));
//        System.out.println(ExpenseTrackerController.addExpense(30,1));
//        System.out.println(ExpenseTrackerController.addExpense(10,1));
//        System.out.println(ExpenseTrackerController.addExpense(20,2));
//        System.out.println(ExpenseTrackerController.addExpense(30,2));
//        System.out.println(ExpenseTrackerController.addExpense(10,2));
//        System.out.println(ExpenseTrackerController.addExpense(30,3));
//        System.out.println(ExpenseTrackerController.addExpense(20,3));
//        System.out.println(ExpenseTrackerController.addExpense(50,0));
//        System.out.println(ExpenseTrackerController.addExpense(40,0));

    }

}
