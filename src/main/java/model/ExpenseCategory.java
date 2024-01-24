package model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class ExpenseCategory extends Category{

    private Map<YearMonth,BigDecimal> budgetLimits = new HashMap<>();
    public static final BigDecimal zero = new BigDecimal(0);

    public ExpenseCategory(String name, int categoryId, BigDecimal budget, YearMonth yearMonth) {
        super(name, categoryId);
        budgetLimits.put(yearMonth, budget);
    }

    public String toString(YearMonth yearMonth) {
        return categoryId + "\t| " + name + "\t| " + getBudgetLimit(yearMonth).toString();
    }

    public BigDecimal getBudgetLimit(YearMonth yearMonth) {
        return budgetLimits.getOrDefault(yearMonth, zero);
    }

    public void setBudgetLimit(YearMonth yearMonth,BigDecimal budgetLimit) {
        this.budgetLimits.put(yearMonth,budgetLimit);
    }
}
