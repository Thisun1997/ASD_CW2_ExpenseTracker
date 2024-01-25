package controller;

import model.Category;
import model.Expense;
import model.Income;
import model.Transaction;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Scanner;

public class MainController {

    private static Scanner scanner;
    private static Controller controller;
    private static YearMonth yearMonth;

    private static void initialize() {
        yearMonth = YearMonth.now();
        scanner = new Scanner(System.in);
    }

    private static void initializeController(int choice) {
        if (choice == 1) {
            controller = IncomeController.getInstance(scanner, yearMonth);
        } else if (choice == 2) {
            controller = ExpenseController.getInstance(scanner, yearMonth);
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static void execute() {
        initialize();
        System.out.println("Welcome to the Expense Tracking App! \n");

        while (true) {
            printSeparator();
            System.out.println("Year :" + yearMonth.getYear() + " Month: " + yearMonth.getMonth());
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

    private static void handleCategories() {
        int subChoice;
        System.out.println("*****Categories*****");
        System.out.println("Category Types\n1:Income\n2:Expense");
        subChoice = getCommand(scanner);
        printSeparator();
        initializeController(subChoice);
        boolean exitLoop = false;
        while (!exitLoop) {
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

    private static void handleTransactions() {
        System.out.println("Year :" + yearMonth.getYear() + " Month: " + yearMonth.getMonth());
        int subChoice;
        boolean exitLoop = false;
        while (!exitLoop) {
            printSeparator();
            showTransactions();
            System.out.println("1: Add Transaction\t2: Delete Transaction\t3: Update Transaction\t4: Exit");
            subChoice = getCommand(scanner);
            switch (subChoice) {
                case 1 -> {
                    addTransaction();
                }
                case 2 -> {
                    deleteTransaction();
                }
                case 3 -> {
                    editTransaction();
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

    private static void handleProgress(){
        int subChoice;
        System.out.println("*****Budget*****");
        printSeparator();
        initializeController(2);
        boolean exitLoop = false;
        while(!exitLoop){
            printSeparator();
            System.out.println("1: Set Budget\t2: Check Progress\t3: Exit\t");
            subChoice = getCommand(scanner);
            switch (subChoice) {
                case 1 -> {
                    setBudget();
                }
                case 2 -> {
                    getProgress();
                }
                case 3 -> {
                    exitLoop = true;
                }
                default -> {
                    System.out.println("Invalid choice. Exiting to home.");
                    exitLoop = true;
                }

            }
        }

    }

    private static void setBudget(){
        CommonController.setBudget(scanner, yearMonth);
    }

    private static void getProgress(){
        CommonController.showProgress(yearMonth);
    }

    private static void editTransaction() {
        int subChoice;
        printSeparator();

        if(CommonController.getTransactions(yearMonth).isEmpty()) {
            System.out.println("No Transactions available. Exiting to previous menu.");
            return;  // Go back to the previous menu
        }
        showTransactions();
        System.out.println("*****Select the Transaction Id You want to edit*****");
        subChoice = getCommand(scanner);

        Transaction transaction = CommonController.getTransactionById(yearMonth, subChoice);

        if(transaction == null){
            System.out.println("No Transactions for provided Id. Please provide valid Id");
            return;
        }

        Category selectedCategory;

        if (transaction instanceof Income) {
            controller = IncomeController.getInstance(scanner, yearMonth);
            System.out.println("Current Category: " + ((Income) transaction).getCategory().getName());
            controller.showCategories();
            System.out.print("Please enter the category id: ");
            String input = scanner.nextLine();
            selectedCategory = input.isEmpty() ? ((Income) transaction).getCategory() : controller.getCategoryById(Integer.parseInt(input));
        } else if (transaction instanceof Expense) {
            controller = ExpenseController.getInstance(scanner, yearMonth);
            System.out.println("Current Category: " + ((Expense) transaction).getCategory().getName());
            controller.showCategories();
            System.out.print("Please enter the category id: ");
            String input = scanner.nextLine();
            selectedCategory = input.isEmpty() ? ((Expense) transaction).getCategory() : controller.getCategoryById(Integer.parseInt(input));
        } else {
            System.out.println("Unknown transaction type. Cannot edit.");
            return;
        }

        System.out.println("\nCurrent Amount: " + transaction.getAmount());
        System.out.print("Enter amount: ");
        String amountInput = scanner.nextLine();
        BigDecimal amount = amountInput.isEmpty() ? transaction.getAmount() : new BigDecimal(amountInput);

        System.out.println("\nCurrent Note: " + transaction.getNote());
        System.out.print("Enter note: ");
        String note = scanner.nextLine();
        note = note.isEmpty() ? transaction.getNote() : note;

        System.out.println("\nCurrent Date: " + transaction.getDate());
        System.out.print("Enter the transaction Date: ");
        String currentDateInput = scanner.nextLine();
        String currentDate = currentDateInput.isEmpty() ? transaction.getDate() : currentDateInput;

        System.out.println("\nCurrent Recurring Status: " + transaction.isRecurring());
        System.out.print("Is it a recurring transaction? (true/false): ");
        String recurringInput = scanner.nextLine();
        boolean isRecurring = recurringInput.isEmpty() ? transaction.isRecurring() : Boolean.parseBoolean(recurringInput);

        // Update the transaction using the initialized controller
        controller.updateTransaction(yearMonth, Integer.parseInt(transaction.getId()), selectedCategory, amount, note, currentDate, isRecurring);
    }

    private static void deleteTransaction() {
        int subChoice;
        printSeparator();

        if(CommonController.getTransactions(yearMonth).isEmpty()) {
            System.out.println("No Transactions available. Exiting to previous menu.");
            return;  // Go back to the previous menu
        }
        showTransactions();
        System.out.println("*****Select the Transaction Id You want to delete*****");
        subChoice = getCommand(scanner);
        CommonController.deleteTransaction(subChoice, yearMonth);
    }

    private static void addTransaction() {
        int subChoice;
        System.out.println("*****Select the Transaction Type*****");
        System.out.println("Transaction Types\n1:Income\n2:Expense");
        subChoice = getCommand(scanner);
        printSeparator();
        initializeController(subChoice);

        if (controller.getCategories().isEmpty()) {
            System.out.println("No categories available. Exiting to previous menu.");
            return;  // Go back to the previous menu
        }

        controller.showCategories();

        System.out.print("Please enter the category id: ");
        int choice = getCommand(scanner);
        Category selectedCategory = controller.getCategoryById(choice);

        System.out.print("Enter amount: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        System.out.print("Enter note: ");
        String note = scanner.nextLine();

        System.out.print("Enter the transaction Date: ");
        String currentDate = scanner.nextLine();

        System.out.print("Is it a recurring transaction? (true/false): ");
        boolean isRecurring = Boolean.parseBoolean(scanner.nextLine());

        controller.addTransaction(yearMonth, currentDate, amount, note, selectedCategory, isRecurring);
    }

    private static void showTransactions() {
        CommonController.showTransactions(yearMonth);
    }

    private static void changeYearMonth() {
        try {
            System.out.print("Add year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Add month: ");
            int month = Integer.parseInt(scanner.nextLine());

            yearMonth = YearMonth.of(year, month);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void showCategories() {
        controller.showCategories();
    }

    private static void addCategory() {
        System.out.println("Category added successfully with category Id: " + controller.addCategory());
    }

    public static int getCommand(Scanner scanner) {
        int choice;
        while (true) {
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

    public static void printSeparator() {
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
