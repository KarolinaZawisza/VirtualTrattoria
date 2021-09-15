package com.company.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable {
    private String author;
    private List<Pizza> orderedPizzas;
    private Long id;

    public Order(String author) {
        this.author = author;
        this.orderedPizzas = new LinkedList<>();
        this.id = -1L;
    }

    public void addPizza(Pizza pizza) {
        orderedPizzas.add(pizza);
    }

    public boolean isEmpty() {
        return orderedPizzas.isEmpty();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "author='" + author + '\'' +
                ", orderedPizzas=" + orderedPizzas +
                ", id=" + id +
                '}';
    }
}
