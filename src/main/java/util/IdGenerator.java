package util;

public class IdGenerator {
    private static int nextCategoryId = 0;

    public static int getNextCategoryId() {
        return nextCategoryId++;
    }
}
