package com.recipebook;

public class VegetarianRecipe extends Recipe {

    public VegetarianRecipe(int id, String name, String[] ingredients,
                            String instructions, boolean favourite) {

        super(id, name, ingredients, instructions, favourite);
    }

    @Override
    public String getTypeLabel() {
        return "Vegetarian";
    }
}
