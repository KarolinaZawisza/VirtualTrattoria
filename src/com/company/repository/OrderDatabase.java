package com.company.repository;

import com.company.entity.Order;

import java.util.LinkedList;
import java.util.List;

public class OrderDatabase {

    private List<Order> orders;

    public OrderDatabase() {
        this.orders = new LinkedList<Order>();
    }
}
