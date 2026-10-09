package com.recipebook;

public abstract class Recipe {

    private int id;
    private String name;
    private String instructions;
    private String[] ingredients;
    private boolean favourite;

    public Recipe(int id, String name, String[] ingredients,
                  String instructions, boolean favourite) {

        this.id = id;
        this.name = name;
        this.ingredients = ingredients.clone();
        this.instructions = instructions;
        this.favourite = favourite;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public String[] getIngredients() {
        return ingredients.clone();
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public abstract String getTypeLabel();
}
