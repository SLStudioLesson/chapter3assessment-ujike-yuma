package com.recipeapp.datahandler;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    // ファイル名
    private String filePath;

    public CSVDataHandler() {
        filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    };

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String lineText;
        ArrayList<Recipe> recipeList = new ArrayList<>();

        // 1行ずつ読み取った文字列を処理
        while ((lineText = reader.readLine()) != null) {
            ArrayList<Ingredient> ingredientList = new ArrayList<>();
            // 文字列をレシピ名と材料に分ける
            String[] pairRecipe = lineText.split(",", 2);
            
            for (String ingredient : pairRecipe[1].split(", ")) {
                ingredientList.add(new Ingredient(ingredient));
            }

            recipeList.add(new Recipe(pairRecipe[0], ingredientList));
        }
        return recipeList;
    };

    @Override
    public void writeData(Recipe recipe) throws IOException {
        //引数recipeを書き込む処理
    };

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    };
}
