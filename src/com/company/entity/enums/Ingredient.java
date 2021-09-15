package com.company.entity.enums;

public enum Ingredient {
    CHEESE("Cheese"),
    TOMATO("Tomato"),
    BASIL("Basil"),
    HAM("Ham"),
    SALAMI("Salami"),
    MUSHROOMS("Mushrooms");

    private String name;

    Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
