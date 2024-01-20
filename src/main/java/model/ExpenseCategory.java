package model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class ExpenseCategory extends Category{
    public static YearMonth currentYearMonth;
    private Map<YearMonth,BigDecimal> budgetLimits = new HashMap<>();
    public static final BigDecimal zero = new BigDecimal(0);

    public ExpenseCategory(String name, int categoryId, BigDecimal budget) {
        super(name, categoryId);
        budgetLimits.put(currentYearMonth,budget);
    }

    @Override
    public String toString() {
        return categoryId + "\t| " + name + "\t| " + getBudgetLimit(currentYearMonth).toString();
    }

    public BigDecimal getBudgetLimit(YearMonth yearMonth) {
        return budgetLimits.getOrDefault(yearMonth, zero);
    }

    public void setBudgetLimit(YearMonth yearMonth,BigDecimal budgetLimit) {
        this.budgetLimits.put(yearMonth,budgetLimit);
    }
}
