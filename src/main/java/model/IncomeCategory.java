package model;

public class IncomeCategory extends Category{
    public IncomeCategory(String name, int categoryId){
        super(name,categoryId);
    }

    @Override
    public String toString() {
        return categoryId + "\t| " + name;
    }

}
