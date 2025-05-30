package com.restaurant;

import com.restaurant.model.*;
import java.util.*;

public class RestaurantSystem {
    private List<User> users;
    private List<MenuItem> menu;
    private List<Order> orders;
    private int nextOrderNumber;

    public RestaurantSystem() {
        this.users = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.nextOrderNumber = 1;
    }

    // User Management Methods
    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(String userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }

    public User findUser(String userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Menu Management Methods
    public void addMenuItem(String name, double price, String category) {
        MenuItem item = new MenuItem(name, price, category);
        menu.add(item);
    }

    public void removeMenuItem(String name) {
        menu.removeIf(item -> item.getName().equals(name));
    }

    public MenuItem findMenuItem(String name) {
        return menu.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateMenuItemAvailability(String name, boolean available) {
        MenuItem item = findMenuItem(name);
        if (item != null) {
            item.setAvailable(available);
        }
    }

    // Order Management Methods
    public Order createOrder(String customerId) {
        Customer customer = (Customer) findUser(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        String orderNumber = String.format("ORD%04d", nextOrderNumber++);
        Order order = new Order(orderNumber, customer);
        orders.add(order);
        customer.addOrder(order);
        return order;
    }

    public void addItemToOrder(String orderNumber, String itemName, int quantity) {
        Order order = findOrder(orderNumber);
        MenuItem item = findMenuItem(itemName);

        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        if (item == null) {
            throw new IllegalArgumentException("Menu item not found");
        }
        if (!item.isAvailable()) {
            throw new IllegalStateException("Item is not available");
        }

        order.addItem(item, quantity);
    }

    public void setOrderPaymentMethod(String orderNumber, Order.PaymentMethod paymentMethod) {
        Order order = findOrder(orderNumber);
        if (order != null) {
            order.setPaymentMethod(paymentMethod);
        }
    }

    public void completeOrder(String orderNumber) {
        Order order = findOrder(orderNumber);
        if (order != null) {
            order.setStatus(Order.OrderStatus.COMPLETED);
        }
    }

    public Order findOrder(String orderNumber) {
        return orders.stream()
                .filter(order -> order.getOrderNumber().equals(orderNumber))
                .findFirst()
                .orElse(null);
    }

    // Utility Methods
    public List<MenuItem> getMenu() {
        return new ArrayList<>(menu);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getCustomerOrders(String customerId) {
        Customer customer = (Customer) findUser(customerId);
        return customer != null ? customer.getOrderHistory() : new ArrayList<>();
    }

    public String generateOrderBill(String orderNumber) {
        Order order = findOrder(orderNumber);
        return order != null ? order.generateBill() : "Order not found";
    }
} 