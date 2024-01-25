package util;

public class RecurringIdGenerator {
    private static int nextTransactionId = 0;

    public static int getNextTransactionId() {
        return nextTransactionId++;
    }
}
