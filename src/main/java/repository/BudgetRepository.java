package repository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class BudgetRepository {

    private static final Map<YearMonth, BigDecimal> budgetMap = new HashMap<>();

    public static BigDecimal setBudget(BigDecimal budget, YearMonth yearMonth) {
        return budgetMap.put(yearMonth, budget);
    }

    public static boolean isBudgetAdded(YearMonth yearMonth) {
        return budgetMap.containsKey(yearMonth);
    }

    public static BigDecimal getBudget(YearMonth yearMonth) {
        return budgetMap.getOrDefault(yearMonth, BigDecimal.valueOf(0));
    }
}
