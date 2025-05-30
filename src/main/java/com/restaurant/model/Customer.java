package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Order> orderHistory;

    public Customer(String name, String id) {
        super(name, id);
        this.orderHistory = new ArrayList<>();
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", orders=" + orderHistory.size() +
                '}';
    }
} 