package com.recipeapp.model;
import java.util.ArrayList;

public class Recipe {
    // レシピ名
    private String name;
    // 材料を格納するリスト
    private ArrayList<Ingredient> ingredients;

    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
