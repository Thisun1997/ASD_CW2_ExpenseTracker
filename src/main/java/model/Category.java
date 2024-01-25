package model;

import java.time.YearMonth;

public abstract class Category {

    protected String name;


    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
