package com.company.entity;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private String author;
    private List<Pizza> orderedPizzas;

    public Order(String author) {
        this.author = author;
        this.orderedPizzas = new LinkedList<>();
    }

    public void addPizza(Pizza pizza) {
        orderedPizzas.add(pizza);
    }
}
