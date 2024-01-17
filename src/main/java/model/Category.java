package model;

import util.IdGenerator;

public class Category {

    //required
    private String name;
    private int categoryId;
    private double budgetLimit;
    private int month;

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
    public int getMonth() {
        return month;
    }

    public static CategoryBuilder modifier(Category category) {
        return new CategoryBuilder(category.getName(), category.getCategoryId());
    }

    private Category(CategoryBuilder builder) {
        this.name = builder.name;
        this.categoryId = builder.categoryId;
        this.budgetLimit = builder.budgetLimit;
        this.month = builder.month;
    }

    public static class CategoryBuilder {

        //required
        private String name;
        private int categoryId;

        //optional
        private double budgetLimit;
        private int month;

        public CategoryBuilder(String name, int categoryId) {
            this.name = name;
            this.categoryId = categoryId;
        }

        public CategoryBuilder setBudgetLimit(double budgetLimit) {
            this.budgetLimit = budgetLimit;
            return this;
        }

        public CategoryBuilder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
