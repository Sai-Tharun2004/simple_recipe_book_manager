# Simple Recipe Book Manager — Core Java

## 📌 Project Overview

The **Simple Recipe Book Manager** is a console-based application developed using Core Java. It helps users create, manage, search, and organize vegetarian and non-vegetarian recipes. The application demonstrates Object-Oriented Programming (OOP) concepts, Java Collections, exception handling, and file handling.

Recipe information is stored in text files so that saved recipes can be loaded again when the application starts.

## ✨ Features

* **Add Recipes:** Create vegetarian and non-vegetarian recipes with a name, three ingredients, and instructions.
* **View All Recipes:** Display the available recipes with their IDs, names, and types.
* **Search Recipes:** Search recipes by name or ingredient.
* **View Recipe Details:** Display complete details of a recipe using its ID.
* **Manage Favourites:** Mark or unmark recipes as favourites and view favourite recipes.
* **Duplicate Name Prevention:** Prevent duplicate recipe names using a `HashSet`.
* **Collection Report:** Generate a report showing total recipes, vegetarian recipes, non-vegetarian recipes, and favourites.
* **File Handling:** Save and load recipe details using text files and save collection reports.
* **Input Validation:** Handle invalid menu choices, blank inputs, and invalid numeric input.

## 🛠️ Technologies Used

* **Programming Language:** Java
* **IDE:** Eclipse
* **Collections:** ArrayList and HashSet
* **Data Structures:** Arrays
* **File Handling:** BufferedReader, BufferedWriter, and Java NIO Files API
* **Input Handling:** Scanner

## 🧠 Core Java Concepts

This project demonstrates the following concepts:

* **Encapsulation:** Private fields and methods to control access to recipe data.
* **Abstraction:** An abstract `Recipe` class with an abstract `getTypeLabel()` method.
* **Inheritance:** `VegetarianRecipe` and `NonVegetarianRecipe` extend the `Recipe` class.
* **Polymorphism:** Method overriding to return the appropriate recipe type.
* **Constructors:** Initialize recipe objects and class data.
* **Arrays:** Store ingredients for each recipe.
* **ArrayList:** Manage multiple recipe objects.
* **HashSet:** Prevent duplicate recipe names.
* **Exception Handling:** Handle file operation errors and invalid numeric input.
* **File Persistence:** Store recipes and reports in text files.

## 📂 Project Structure

```text
Simple-Recipe-Book-Manager/
├── src/
│   └── com/
│       └── recipebook/
│           ├── Main.java
│           ├── Recipe.java
│           ├── RecipeService.java
│           ├── FileManager.java
│           ├── VegetarianRecipe.java
│           └── NonVegetarianRecipe.java
├── data/
│   ├── recipes.txt
│   └── report.txt
└── README.md
```

*Note: The `data` folder and text files are created by the application when needed. Their presence in the repository depends on whether you have committed them.*

## ▶️ How to Run

1. Clone or download this repository.
2. Open Eclipse IDE.
3. Import the project into your workspace.
4. Make sure Java is installed and configured.
5. Open `Main.java` in the `com.recipebook` package.
6. Run `Main.java` as a Java application.
7. Use the console menu to manage your recipes.

## 💾 Data Storage

The application uses two text files:

* `data/recipes.txt` — stores recipe information.
* `data/report.txt` — stores the generated collection report.

Recipes are loaded from the saved recipe file when the application starts. Saving and loading must complete successfully for the data to persist between runs.

## 🎯 Learning Outcomes

Through this project, I practised:

* Designing a Java application using multiple classes.
* Applying Object-Oriented Programming concepts.
* Working with arrays and Java Collections.
* Implementing search and duplicate-checking logic.
* Reading and writing text files.
* Connecting user input, business logic, and file storage.

## 👨‍💻 Author

**Sai Tharun**

