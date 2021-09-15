package com.company.entity.enums;

import java.time.chrono.MinguoDate;
import java.util.*;

public enum PizzaType {
    MARGHERITA("Margherita"),
    CAPRICIOSA("Capriciosa"),
    CALZONE("Calzone");

    private static final Map<PizzaType, Set<Ingredient>> pizzaIngredientSet = new HashMap<>();

    private final String name;

    PizzaType(String name) {
        this.name = name;
    }

    public Set<Ingredient> getDefaultIngredients() {
        return  pizzaIngredientSet.get(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    static {
        Set<Ingredient> ingredients = new HashSet<>();

        ingredients.add(Ingredient.BASIL);
        ingredients.add(Ingredient.TOMATO);
        ingredients.add(Ingredient.CHEESE);

        pizzaIngredientSet.put(PizzaType.MARGHERITA, ingredients);

        ingredients = new HashSet<>();

        ingredients.add(Ingredient.BASIL);
        ingredients.add(Ingredient.TOMATO);
        ingredients.add(Ingredient.CHEESE);
        ingredients.add(Ingredient.SALAMI);

        pizzaIngredientSet.put(PizzaType.CALZONE, ingredients);

        ingredients = new HashSet<>();

        ingredients.add(Ingredient.BASIL);
        ingredients.add(Ingredient.TOMATO);
        ingredients.add(Ingredient.CHEESE);
        ingredients.add(Ingredient.MUSHROOMS);
        ingredients.add(Ingredient.HAM);

        pizzaIngredientSet.put(PizzaType.CAPRICIOSA, ingredients);


    }
}
