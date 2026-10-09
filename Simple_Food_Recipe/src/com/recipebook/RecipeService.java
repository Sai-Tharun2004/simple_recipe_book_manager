package com.recipebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class RecipeService {

    private ArrayList<Recipe> recipes;
    private HashSet<String> recipeNames;

    private int nextId = 1;

    public RecipeService() {
        recipes = new ArrayList<>();
        recipeNames = new HashSet<>();
    }

    public boolean addRecipe(String name, String type,
                             String[] ingredients, String instructions) {

        String key = name.trim().toLowerCase(Locale.ROOT);

        if (recipeNames.contains(key)) {
            System.out.println("Recipe name already exists.");
            return false;
        }

        Recipe recipe;

        if (type.equals("VEG")) {
            recipe = new VegetarianRecipe(
                    nextId,
                    name,
                    ingredients,
                    instructions,
                    false
            );
        } else {
            recipe = new NonVegetarianRecipe(
                    nextId,
                    name,
                    ingredients,
                    instructions,
                    false
            );
        }

        recipes.add(recipe);
        recipeNames.add(key);

        System.out.println("Recipe " + nextId + " added successfully.");

        nextId++;

        return true;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Recipe findById(int id) {

        for (Recipe recipe : recipes) {

            if (recipe.getId() == id) {
                return recipe;
            }
        }

        return null;
    }

    public void showAllRecipes() {

        if (recipes.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Recipe recipe : recipes) {

            System.out.println(
                    recipe.getId() + " | "
                    + recipe.getName() + " | "
                    + recipe.getTypeLabel()
            );
        }
    }

    public void search(String keyword) {

        if (recipes.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        boolean found = false;

        keyword = keyword.toLowerCase(Locale.ROOT);

        for (Recipe recipe : recipes) {

            boolean match = recipe.getName()
                    .toLowerCase(Locale.ROOT)
                    .contains(keyword);

            if (!match) {

                String[] ingredients = recipe.getIngredients();

                for (String ingredient : ingredients) {

                    if (ingredient.toLowerCase(Locale.ROOT)
                            .contains(keyword)) {

                        match = true;
                        break;
                    }
                }
            }

            if (match) {

                System.out.println(
                        recipe.getId() + " | "
                        + recipe.getName() + " | "
                        + recipe.getTypeLabel()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found.");
        }
    }

    public boolean markFavourite(int id) {

        Recipe recipe = findById(id);

        if (recipe == null) {
            return false;
        }

        recipe.setFavourite(!recipe.isFavourite());

        return true;
    }

    public void showFavourites() {

        boolean found = false;

        for (Recipe recipe : recipes) {

            if (recipe.isFavourite()) {

                System.out.println(
                        recipe.getId() + " | "
                        + recipe.getName() + " | "
                        + recipe.getTypeLabel()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No favourite recipes.");
        }
    }

    public String showReport() {

        int total = recipes.size();
        int vegetarian = 0;
        int nonVegetarian = 0;
        int favourites = 0;

        for (Recipe recipe : recipes) {

            if (recipe instanceof VegetarianRecipe) {
                vegetarian++;
            }

            if (recipe instanceof NonVegetarianRecipe) {
                nonVegetarian++;
            }

            if (recipe.isFavourite()) {
                favourites++;
            }
        }

        String report =
                "Total recipes: " + total + "\n"
                + "Vegetarian recipes: " + vegetarian + "\n"
                + "Nonvegetarian recipes: " + nonVegetarian + "\n"
                + "Favourite recipes: " + favourites;

        System.out.println("\nCOLLECTION REPORT");
        System.out.println("-----------------");
        System.out.println(report);

        return report;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public void loadRecipe(Recipe recipe) {

        recipes.add(recipe);

        String key = recipe.getName()
                .trim()
                .toLowerCase(Locale.ROOT);

        recipeNames.add(key);
    }
}