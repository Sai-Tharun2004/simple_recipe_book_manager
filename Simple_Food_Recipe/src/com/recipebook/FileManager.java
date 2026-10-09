package com.recipebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class FileManager {

    private final Path dataFolder = Path.of("data");
    private final Path recipeFile = dataFolder.resolve("recipes.txt");
    private final Path reportFile = dataFolder.resolve("report.txt");

    public FileManager() {

        try {
            Files.createDirectories(dataFolder);
        } catch (IOException e) {
            System.out.println("Unable to create data folder.");
        }
    }

    public void saveRecipes(ArrayList<Recipe> recipes) {
        Path tempFile = dataFolder.resolve("recipes_temp.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(tempFile,StandardCharsets.UTF_8)) {
            for (Recipe recipe : recipes) {
                String[] ingredients = recipe.getIngredients();
                String line =
                        recipe.getId() + "|"
                        + (recipe instanceof VegetarianRecipe
                                ? "VEG" : "NONVEG") + "|"
                        + clean(recipe.getName()) + "|"
                        + clean(ingredients[0]) + "|"
                        + clean(ingredients[1]) + "|"
                        + clean(ingredients[2]) + "|"
                        + clean(recipe.getInstructions()) + "|"
                        + recipe.isFavourite();

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving recipes: " + e.getMessage());
            return;
        }

        try {
            Files.move(
                    tempFile,
                    recipeFile,
                    StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println("Recipes saved successfully.");
        } catch (IOException e) {
            System.out.println("Error replacing recipe file: "+ e.getMessage());
        }
    }

    public ArrayList<Recipe> loadRecipes() {
        ArrayList<Recipe> loadedRecipes = new ArrayList<>();
        if (!Files.exists(recipeFile)) {
            return loadedRecipes;
        }
        try (BufferedReader reader = Files.newBufferedReader(
                recipeFile,
                StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] fields = line.split("\\|", -1);
                if (fields.length != 8) {
                    System.out.println("Invalid record in recipes.txt at line "+ lineNumber);
                    return new ArrayList<>();
                }

                int id = Integer.parseInt(fields[0]);
                String type = fields[1];
                String name = fields[2];
                String[] ingredients = {
                        fields[3],
                        fields[4],
                        fields[5]
                };
                String instructions = fields[6];
                String favouriteText = fields[7];
                if (!type.equals("VEG")&& !type.equals("NONVEG")) {
                    System.out.println("Invalid type at line " + lineNumber);
                    return new ArrayList<>();
                }

                if (!favouriteText.equals("true")&& !favouriteText.equals("false")) {
                    System.out.println("Invalid favourite value at line "+ lineNumber);
                    return new ArrayList<>();
                }

                boolean favourite =Boolean.parseBoolean(favouriteText);
                Recipe recipe;

                if (type.equals("VEG")) {
                    recipe = new VegetarianRecipe(
                            id,
                            name,
                            ingredients,
                            instructions,
                            favourite
                    );
                } 
                else {
                    recipe = new NonVegetarianRecipe(
                            id,
                            name,
                            ingredients,
                            instructions,
                            favourite
                    );
                }
                loadedRecipes.add(recipe);
            }
        } catch (IOException e) {
            System.out.println("Error reading recipes.txt: "+ e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID in recipes.txt.");
        }
        return loadedRecipes;
    }

    public void saveReport(String report) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                reportFile,
                StandardCharsets.UTF_8)) {
            writer.write(report);
            System.out.println("Report saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving report: "+ e.getMessage());
        }
    }

    private String clean(String text) {
        return text
                .replace("|", " ")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}
