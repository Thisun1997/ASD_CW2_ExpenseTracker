package model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class ExpenseCategory extends Category{
    private static final BigDecimal zero = new BigDecimal(0);
    private final Map<YearMonth,BigDecimal> budgetLimits = new HashMap<>();

    public ExpenseCategory(String name,YearMonth yearMonth, BigDecimal budget) {
        super(name);
        budgetLimits.put(yearMonth,budget);
    }
    public ExpenseCategory(String name) {
        super(name);
    }

    public BigDecimal getBudgetLimit(YearMonth yearMonth) {
        return budgetLimits.getOrDefault(yearMonth, zero);
    }

    public void setBudgetLimit(YearMonth yearMonth,BigDecimal budgetLimit) {
        this.budgetLimits.put(yearMonth,budgetLimit);
    }
}
