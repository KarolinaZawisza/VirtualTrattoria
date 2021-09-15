package com.company.services;

import com.company.entity.Order;
import com.company.repository.OrderDatabase;
import com.company.utils.ConsoleUtils;

import java.util.Objects;

public class OrderService {

    FileService fileService = new FileService();
    OrderDatabase orderDatabase = new OrderDatabase();

    public void addOrderToDatabase(Order order) {
        validateOrder(order);
        orderDatabase.add(order);

        ConsoleUtils.writeToConsole(orderDatabase.toString());
    }

    private void validateOrder(Order order) {
        if(order.isEmpty()) {
            throw new IllegalArgumentException("Order has to contains at least one pizza. Please place order once again.");
        }
    }

    public void loadDatabaseFromFile(String filename) {
        OrderDatabase orderDatabaseFromFile = fileService.readOrderDatabaseFromFile(filename);
        orderDatabase = Objects.requireNonNullElseGet(orderDatabaseFromFile, OrderDatabase::new);
    }

    public void saveDatabaseToFile(String filename) {
        fileService.writeOrderDatabaseToFile(orderDatabase, filename);
    }

    public String getDatabaseAsString() {
        return orderDatabase.toString();
    }
}
