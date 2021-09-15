package com.company.services;

public class ServiceHelper {
    private CommandService commandService = new CommandService();
    private OrderService orderService = new OrderService();

    public CommandService getCommandService() {
        return commandService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
