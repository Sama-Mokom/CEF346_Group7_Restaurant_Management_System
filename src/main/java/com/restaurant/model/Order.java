package com.restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderNumber;
    private Customer customer;
    private List<OrderItem> items;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private LocalDateTime orderTime;
    private double totalAmount;

    public enum PaymentMethod {
        BANK_TRANSFER,
        CARD,
        MOBILE_PAYMENT,
        CASH
    }

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }

    public Order(String orderNumber, Customer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderTime = LocalDateTime.now();
        this.totalAmount = 0.0;
    }

    public void addItem(MenuItem item, int quantity) {
        if (item.isAvailable()) {
            OrderItem orderItem = new OrderItem(item, quantity);
            items.add(orderItem);
            calculateTotal();
        } else {
            throw new IllegalStateException("Item " + item.getName() + " is not available");
        }
    }

    public void removeItem(MenuItem item) {
        items.removeIf(orderItem -> orderItem.getMenuItem().equals(item));
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String generateBill() {
        StringBuilder bill = new StringBuilder();
        bill.append("\n=== ORDER BILL ===\n");
        bill.append("Order Number: ").append(orderNumber).append("\n");
        bill.append("Customer: ").append(customer.getName()).append("\n");
        bill.append("Order Time: ").append(orderTime).append("\n");
        bill.append("Items:\n");
        
        for (OrderItem item : items) {
            bill.append(String.format("  %s x%d - %,.2f XAF\n",
                    item.getMenuItem().getName(),
                    item.getQuantity(),
                    item.getMenuItem().getPrice() * item.getQuantity()));
        }
        
        bill.append("Total Amount: ").append(String.format("%,.2f XAF", totalAmount)).append("\n");
        bill.append("Payment Method: ").append(paymentMethod != null ? paymentMethod : "Not Set").append("\n");
        bill.append("Status: ").append(status).append("\n");
        bill.append("================\n");
        
        return bill.toString();
    }
} 