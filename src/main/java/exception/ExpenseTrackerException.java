package exception;

public class ExpenseTrackerException extends RuntimeException{
    public ExpenseTrackerException(String message) {
        super(message);
    }

    public ExpenseTrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
