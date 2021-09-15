package com.company.repository;

import com.company.entity.Order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class OrderDatabase implements Serializable {

    private List<Order> orders;
    private Long nextId;

    public OrderDatabase() {
        this.orders = new LinkedList<>();
        this.nextId = 1L;
    }

    public Long add(Order order) {
        order.setId(obtainNextId());
        orders.add(order);
        return order.getId();
    }

    private Long obtainNextId() {
        return nextId++;
    }

    @Override
    public String toString() {
        return "OrderDatabase{" +
                "orders=" + orders +
                ", nextId=" + nextId +
                '}';
    }
}
