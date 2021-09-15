package com.company.controllers;

import com.company.console.ExecutionControlListener;
import com.company.entity.Order;
import com.company.entity.Pizza;
import com.company.entity.enums.Ingredient;
import com.company.entity.enums.PizzaType;
import com.company.utils.ConsoleUtils;

import java.io.Console;

public class Controller {

    private ExecutionControlListener executionControlListener;

    public Controller(ExecutionControlListener executionControlListener) {
        this.executionControlListener = executionControlListener;
    }

    public void loadData() {
        //TODO
    }

    public void saveData() {
        //TODO
    }

    public void execute() {
        String author;
        do {
            ConsoleUtils.writeToConsole("Author:");
            author = ConsoleUtils.readStringFromUser();
        } while (ifAuthorIsCorrect(author));
        Order order = new Order(author);

        PizzaType pizzaType = null;
        do {
            ConsoleUtils.writeToConsole("Type:");
            String stringFromUser = ConsoleUtils.readStringFromUser();
            if(stringFromUser != null) {
                pizzaType = obtainPizzaType(stringFromUser);
            }
        } while (pizzaType == null);

        Pizza pizza = new Pizza(pizzaType);
        ConsoleUtils.writeToConsole(pizza.toString());

        executionControlListener.stopExecution();
    }

    private PizzaType obtainPizzaType(String stringFromUser) {
        PizzaType pizzaType = null;
        try {
            pizzaType = PizzaType.valueOf(stringFromUser.toUpperCase());
        } catch (IllegalArgumentException e) {
            ConsoleUtils.writeToConsole("Please type correctly you Fucking Bastard.");
        }
        return pizzaType;
    }

    private boolean ifAuthorIsCorrect(String author) {
        return author == null || author.equals("");
    }
}
