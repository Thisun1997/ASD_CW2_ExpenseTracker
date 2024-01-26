import controller.CategoryInitializer;
import controller.MainController;

public class ExpenseTracker {

    public static void main(String[] args) {

        CategoryInitializer.initialize();
        MainController.execute();

    }
}
