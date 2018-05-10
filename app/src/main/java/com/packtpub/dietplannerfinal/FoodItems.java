package com.packtpub.dietplannerfinal;

public class FoodItems {
    public String name;
    public String calories;
    public String carbohydrate;
    public String protien;
    public String fat;
    public FoodItems()
    {

    }
    public FoodItems(String name, String calories, String carbohydrate,String protien, String fat) {
        this.name = name;
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.protien = protien;
        this.fat = fat;
    }
}
