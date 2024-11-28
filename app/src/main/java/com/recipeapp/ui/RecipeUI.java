package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//追記
import com.recipeapp.datahandler.*;
import com.recipeapp.model.*;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipeList = dataHandler.readData();
            // レシピデータが1件も存在しない場合
            if (recipeList.isEmpty()) {
                System.out.println("\nNo recipes available.");
                return;
            }

            String line = "-----------------------------------";

            System.out.println("\nRecipes:\n" + line);
            // レシピ毎に処理を実行
            for (Recipe recipe : recipeList) {
                // レシピ名出力
                System.out.println("Recipe Name: " + recipe.getName());

                // 材料名出力
                ArrayList<Ingredient> ingredients = recipe.getIngredients();
                // String.join()を使い ", " で結合するための処理
                String[] strs = new String[ingredients.size()];
                for (int i = 0; i < ingredients.size(); i++) {
                    strs[i] = ingredients.get(i).getName();
                }
                System.out.println("Main Ingredients: " + String.join(", ", strs));
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            System.out.println("\nAdding a new recipe.");
            // レシピ名の入力
            System.out.println("Enter recipe name: ");
            String recipeName = reader.readLine();

            // 材料名の入力
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            boolean flag = true;
            while (flag) {
                String ingredientName = reader.readLine();
                switch (ingredientName) {
                    case "done":
                        flag = false;
                        break;
                
                    default:
                        Ingredient ingredient = new Ingredient(ingredientName);
                        ingredients.add(ingredient);
                        break;
                }
            }
            // 新しいrecipeインスタンス生成
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);
            
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
