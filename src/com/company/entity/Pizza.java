package com.company.entity;

import com.company.entity.enums.Ingredient;
import com.company.entity.enums.PizzaType;

import java.io.Serializable;
import java.util.Set;

public class Pizza implements Serializable {
    PizzaType pizzaType;
    Set<Ingredient> ingredients;

    public Pizza(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
        this.ingredients = pizzaType.getDefaultIngredients();
    }

    public boolean addIngredient(Ingredient ingredient) {
        return ingredients.add(ingredient);
    }

    public boolean removeIngredient(Ingredient ingredient) {
        return ingredients.remove(ingredient);
    }

    public boolean hasNoIngredients() {
        return ingredients.isEmpty();
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Pizza " + pizzaType +
                ": " + ingredients;
    }
}
