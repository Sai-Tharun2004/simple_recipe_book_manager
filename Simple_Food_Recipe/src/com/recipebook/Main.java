package com.recipebook;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static RecipeService service = new RecipeService();
    private static FileManager fileManager = new FileManager();
    
    public static void main(String[] args) {
        loadSavedRecipes();
        showMenu();
        scanner.close();
    }

    private static void loadSavedRecipes() {
        ArrayList<Recipe> loadedRecipes =fileManager.loadRecipes();
        int largestId = 0;
        
        for (Recipe recipe : loadedRecipes) {
            service.loadRecipe(recipe);
            if (recipe.getId() > largestId) {
                largestId = recipe.getId();
            }
        }
        service.setNextId(largestId + 1);
    }

    public static void showMenu() {
        while (true) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("     RECIPE BOOK MENU");
            System.out.println("==============================");
            System.out.println("1. Add recipe");
            System.out.println("2. View all recipes");
            System.out.println("3. Search by name or ingredient");
            System.out.println("4. View recipe details");
            System.out.println("5. Mark or unmark favourite");
            System.out.println("6. View favourites");
            System.out.println("7. View and save collection report");
            System.out.println("0. Exit");
            System.out.println("==============================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addRecipe();
                    break;
                case 2:
                    service.showAllRecipes();
                    break;
                case 3:
                    searchRecipe();
                    break;
                case 4:
                    viewRecipeDetails();
                    break;
                case 5:
                    markFavourite();
                    break;
                case 6:
                    service.showFavourites();
                    break;
                case 7:
                    showAndSaveReport();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRecipe() {
        System.out.println("\nADD RECIPE");
        String name;
        while (true) {
            name = readLine("Enter recipe name: ");
            if (name.isEmpty()) {
                System.out.println("Recipe name cannot be blank.");
                continue;
            }
            break;
        }
        String type = chooseRecipeType();
        String[] ingredients = new String[3];
        for (int i = 0; i < 3; i++) {
            while (true) {
                ingredients[i] = readLine("Ingredient " + (i + 1) + ": ");
                if (ingredients[i].isEmpty()) {
                    System.out.println("Ingredient cannot be blank.");
                } 
                else if (ingredients[i].contains("|")) {
                    System.out.println("Ingredient cannot contain |.");
                }
                else {
                    break;
                }
            }
        }
        String instructions;
        while (true) {
            instructions =readLine("Instructions: ");
            if (instructions.isEmpty()) {
                System.out.println("Instructions cannot be blank.");
            } 
            else if (instructions.contains("|")) {

                System.out.println("Instructions cannot contain |.");
            } 
            else {
                break;
            }
        }

        boolean added = service.addRecipe(name,type,ingredients,instructions);
        if (added) {
            fileManager.saveRecipes(service.getRecipes());
        }
    }

    private static String chooseRecipeType() {
        while (true) {

            System.out.println();
            System.out.println("RECIPE TYPES");
            System.out.println("1. Vegetarian");
            System.out.println("2. Nonvegetarian");

            int choice =readInt("Choose recipe type: ");

            if (choice == 1) {
                return "VEG";
            }

            if (choice == 2) {
                return "NONVEG";
            }

            System.out.println("Invalid choice. Please select 1 or 2.");
        }
    }

    private static void searchRecipe() {
        String keyword;
        while (true) {
            keyword =readLine("Enter name or ingredient: ");

            if (!keyword.isEmpty()) {
                break;
            }

            System.out.println("Search text cannot be blank.");
        }
        service.search(keyword);
    }

    private static void viewRecipeDetails() {

        service.showAllRecipes();

        if (service.getRecipes().isEmpty()) {
            return;
        }

        int id =readInt("Enter recipe ID: ");

        Recipe recipe =service.findById(id);

        if (recipe == null) {
            System.out.println("Recipe ID not found.");
            return;
        }

        System.out.println();
        System.out.println("RECIPE DETAILS");
        System.out.println("----------------------");
        System.out.println("ID: " + recipe.getId());
        System.out.println("Name: " + recipe.getName());
        System.out.println("Type: " + recipe.getTypeLabel());
        System.out.println("Ingredients:");

        String[] ingredients =recipe.getIngredients();

        for (int i = 0; i < ingredients.length; i++) {
            System.out.println((i + 1) + ". " + ingredients[i]);
        }
        System.out.println("Instructions: "+ recipe.getInstructions());
        System.out.println("Favourite: "+ recipe.isFavourite());
    }

    private static void markFavourite() {
        service.showAllRecipes();
        if (service.getRecipes().isEmpty()) {
            return;
        }

        int id =readInt("Enter recipe ID: ");

        Recipe recipe =service.findById(id);

        if (recipe == null) {
            System.out.println("Recipe ID not found.");
            return;
        }

        boolean newStatus =!recipe.isFavourite();
        recipe.setFavourite(newStatus);
        fileManager.saveRecipes(service.getRecipes());

        if (newStatus) {
            System.out.println(recipe.getName()+ " marked as favourite.");
        } 
        else {
            System.out.println(recipe.getName()+ " removed from favourites.");
        }
    }

    private static void showAndSaveReport() {

        String report =service.showReport();
        String choice =readLine("Save report? (yes/no): ");

        if (choice.equalsIgnoreCase("yes")) {
            fileManager.saveReport(report);
        }
    }

    private static int readInt(String message) {
        while (true) {

            String input =readLine(message);

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
