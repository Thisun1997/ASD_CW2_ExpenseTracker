package exception;

public class CategoryException extends Throwable {
    public static class CategoryNotFoundException extends ExpenseTrackerException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryHasExpensesException extends ExpenseTrackerException {
        public CategoryHasExpensesException(String message) {
            super(message);
        }
    }

    public static class CategoryNameNotProvidedException extends ExpenseTrackerException {
        public CategoryNameNotProvidedException(String message) {
            super(message);
        }
    }
}
