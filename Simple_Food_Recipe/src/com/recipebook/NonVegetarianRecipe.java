package com.recipebook;

public class NonVegetarianRecipe extends Recipe {

    public NonVegetarianRecipe(int id, String name, String[] ingredients,
                               String instructions, boolean favourite) {

        super(id, name, ingredients, instructions, favourite);
    }

    @Override
    public String getTypeLabel() {
        return "Nonvegetarian";
    }
}
