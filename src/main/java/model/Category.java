package model;

public class Category {

    private String name;
    private int categoryId;
    private double budgetLimit;


    public Category(String name, int categoryId, double budgetLimit) {
        this.name = name;
        this.categoryId = categoryId;
        this.budgetLimit = budgetLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
}
