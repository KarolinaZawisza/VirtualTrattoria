package com.company.controllers;

import com.company.console.ExecutionControlListener;
import com.company.entity.Order;
import com.company.entity.Pizza;
import com.company.entity.enums.Ingredient;
import com.company.entity.enums.PizzaType;
import com.company.services.ServiceHelper;
import com.company.utils.ConsoleUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    private final ExecutionControlListener executionControlListener;
    private final ServiceHelper serviceHelper;

    private static final String FILENAME = "OrderDatabase.bin";


    public Controller(ExecutionControlListener executionControlListener) {
        this.executionControlListener = executionControlListener;
        this.serviceHelper = new ServiceHelper();
    }

    public void loadData() {
        serviceHelper.getOrderService().loadDatabaseFromFile(FILENAME);
    }

    public void saveData() {
        serviceHelper.getOrderService().saveDatabaseToFile(FILENAME);
    }

    public void execute() {
        ConsoleUtils.writeToConsole("Enter \"order\" to place new order or \"exit\" to close the app:");
        String readStringFromUser = ConsoleUtils.readStringFromUser();
        if (readStringFromUser != null) {
            switch (readStringFromUser.toLowerCase()) {
                case "order" -> handleOrderCommand();
                case "show" -> handleShowCommand();
                case "exit" -> handleExitCommand();
                default -> handleIncorrectCommand();
            }
        }
    }

    private void handleShowCommand() {
        String databaseAsString = serviceHelper.getOrderService().getDatabaseAsString();
        ConsoleUtils.writeToConsole(databaseAsString);
    }

    private void handleOrderCommand() {
        String author;
        do {
            ConsoleUtils.writeToConsole("Author (please enter your name):");
            author = ConsoleUtils.readStringFromUser();
        } while (ifAuthorIsCorrect(author));
        Order order = new Order(author);

        while (true) {
            Pizza pizza = obtainPizza();
            if (pizza != null) {
                order.addPizza(pizza);
            } else {
                break;
            }
        }

        serviceHelper.getOrderService().addOrderToDatabase(order);
        ConsoleUtils.writeToConsole(order.toString());
    }

    private Pizza obtainPizza() {
        PizzaType pizzaType = null;
        do {
            ConsoleUtils.writeToConsole("Types available: " + obtainAvailableTypes() + ". If you're done with your order, you can type \"end\" to finish your order.");
            String pizzaTypeStringFromUser = ConsoleUtils.readStringFromUser();
            if (pizzaTypeStringFromUser != null) {
                if (pizzaTypeStringFromUser.equals("end")) {
                    return null;
                } else {
                    pizzaType = obtainPizzaType(pizzaTypeStringFromUser);
                }
            }
        } while (pizzaType == null);

        Pizza pizza = new Pizza(pizzaType);
        alternatePizza(pizza);
        validatePizza(pizza);

        return pizza;
    }

    private void validatePizza(Pizza pizza) {
        if(pizza.hasNoIngredients()) {
            throw new IllegalArgumentException("Pizza has to contains at least one ingredients. Pizza was not added to order.");
        }
    }

    private void alternatePizza(Pizza pizza) {
        ConsoleUtils.writeToConsole("Enter add/remove <ingredient> to alternate pizza, enter done to finish.");

        while (true) {
            Set<Ingredient> availableIngredients = getAvailableIngredients(pizza);
            ConsoleUtils.writeToConsole("Available ingredients: " + availableIngredients);
            ConsoleUtils.writeToConsole("Current ingredients: " + pizza.getIngredients());
            String readStringFromUser = ConsoleUtils.readStringFromUser();
            if (readStringFromUser != null) {
                try {
                    String[] splitedStringFromUser = readStringFromUser.split(" ");
                    String command = splitedStringFromUser[0].toLowerCase();


                    switch (command) {
                        case "add" -> {
                            String ingredientAsString = splitedStringFromUser[1].toUpperCase();
                            handleAddIngredientCommand(pizza, ingredientAsString);
                        }
                        case "remove" -> {
                            String ingredientAsString = splitedStringFromUser[1].toUpperCase();
                            handleRemoveIngredientCommand(pizza, ingredientAsString);
                        }
                        case "done" -> {
                            return;
                        }
                        default -> handleIncorrectCommand();
                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    handleIncorrectCommand();
                }
            }
        }
    }

    private Set<Ingredient> getAvailableIngredients(Pizza pizza) {
        Set<Ingredient> availableIngredients = new HashSet<>(Arrays.asList(Ingredient.values()));
        availableIngredients.removeAll(pizza.getIngredients());
        return availableIngredients;
    }

    private void handleAddIngredientCommand(Pizza pizza, String ingredientAsString) {
        try {
            Ingredient ingredient = Ingredient.valueOf(ingredientAsString);
            pizza.addIngredient(ingredient);
        } catch (IllegalArgumentException e) {
            ConsoleUtils.writeToConsole("Invalid ingredient.");
        }
    }

    private void handleRemoveIngredientCommand(Pizza pizza, String ingredientAsString) {
        try {
            Ingredient ingredient = Ingredient.valueOf(ingredientAsString);
            pizza.removeIngredient(ingredient);
        } catch (IllegalArgumentException e) {
            ConsoleUtils.writeToConsole("Invalid ingredient. Please try again.");
        }
    }

    private String obtainAvailableTypes() {
        return PizzaType.getAllNamesAsString();
    }

    private void handleExitCommand() {
        executionControlListener.stopExecution();
    }

    private void handleIncorrectCommand() {
        ConsoleUtils.writeToConsole("Invalid command. Please try again.");
    }

    private PizzaType obtainPizzaType(String stringFromUser) {
        PizzaType pizzaType = null;
        try {
            pizzaType = PizzaType.valueOf(stringFromUser.toUpperCase());
        } catch (IllegalArgumentException e) {
            ConsoleUtils.writeToConsole("Invalid pizza type. Please try again.");
        }
        return pizzaType;
    }

    private boolean ifAuthorIsCorrect(String author) {
        return author == null || author.equals("");
    }
}
