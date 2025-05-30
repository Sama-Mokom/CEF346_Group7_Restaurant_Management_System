package com.restaurant;

import com.restaurant.model.*;
import java.util.Scanner;

public class Main {
    private static RestaurantSystem system;
    private static Scanner scanner;

    public static void main(String[] args) {
        system = new RestaurantSystem();
        scanner = new Scanner(System.in);

        // Initialize some test data
        initializeTestData();

        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleUserManagement();
                    break;
                case 2:
                    handleMenuManagement();
                    break;
                case 3:
                    handleOrderManagement();
                    break;
                case 4:
                    System.out.println("Thank you for using the Restaurant Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeTestData() {
        // Add sample users
        system.addUser(new Customer("Sama Mokom", "CUST001"));
        system.addUser(new Customer("Fred Matike", "CUST002"));
        system.addUser(new Customer("Mark Grayson", "CUST003"));
        system.addUser(new Customer("Nolan Grayson", "CUST004"));
        system.addUser(new Staff("Jane Innes", "STAFF001", "Waiter"));
        system.addUser(new Management("Mike Ross", "MGR001", "Operations"));

        // Add sample menu items
        system.addMenuItem("Burger", 2500, "Main Course");
        system.addMenuItem("Pizza", 15000, "Main Course");
        system.addMenuItem("Salad", 1500, "Appetizer");
        system.addMenuItem("Ice Cream", 1000, "Dessert");
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Restaurant Management System ===");
        System.out.println("1. User Management");
        System.out.println("2. Menu Management");
        System.out.println("3. Order Management");
        System.out.println("4. Exit");
    }

    private static void handleUserManagement() {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleMenuManagement() {
        while (true) {
            System.out.println("\n=== Menu Management ===");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Update Item Availability");
            System.out.println("4. View Menu");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    updateItemAvailability();
                    break;
                case 4:
                    viewMenu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleOrderManagement() {
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Create New Order");
            System.out.println("2. Add Item to Order");
            System.out.println("3. Set Payment Method");
            System.out.println("4. Complete Order");
            System.out.println("5. View Order Bill");
            System.out.println("6. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    addItemToOrder();
                    break;
                case 3:
                    setOrderPaymentMethod();
                    break;
                case 4:
                    completeOrder();
                    break;
                case 5:
                    viewOrderBill();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper methods for user input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Implementation of specific operations
    private static void addUser() {
        System.out.println("\nSelect user type:");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Management");

        int type = getIntInput("Enter choice: ");
        String name = getStringInput("Enter name: ");
        String id = getStringInput("Enter ID: ");

        try {
            switch (type) {
                case 1:
                    system.addUser(new Customer(name, id));
                    break;
                case 2:
                    String role = getStringInput("Enter role: ");
                    system.addUser(new Staff(name, id, role));
                    break;
                case 3:
                    String department = getStringInput("Enter department: ");
                    system.addUser(new Management(name, id, department));
                    break;
                default:
                    System.out.println("Invalid user type.");
                    return;
            }
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    private static void removeUser() {
        String userId = getStringInput("Enter user ID to remove: ");
        system.removeUser(userId);
        System.out.println("User removed successfully.");
    }

    private static void addMenuItem() {
        String name = getStringInput("Enter item name: ");
        double price = getDoubleInput("Enter price: ");
        String category = getStringInput("Enter category: ");

        try {
            system.addMenuItem(name, price, category);
            System.out.println("Menu item added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding menu item: " + e.getMessage());
        }
    }

    private static void removeMenuItem() {
        String name = getStringInput("Enter item name to remove: ");
        system.removeMenuItem(name);
        System.out.println("Menu item removed successfully.");
    }

    private static void updateItemAvailability() {
        String name = getStringInput("Enter item name: ");
        System.out.println("Is item available? (1: Yes, 0: No)");
        int available = getIntInput("Enter choice: ");
        
        system.updateMenuItemAvailability(name, available == 1);
        System.out.println("Item availability updated successfully.");
    }

    private static void viewMenu() {
        System.out.println("\n=== Current Menu ===");
        for (MenuItem item : system.getMenu()) {
            System.out.println(item);
        }
    }

    private static void createOrder() {
        String customerId = getStringInput("Enter customer ID: ");
        try {
            Order order = system.createOrder(customerId);
            System.out.println("Order created successfully. Order number: " + order.getOrderNumber());
        } catch (Exception e) {
            System.out.println("Error creating order: " + e.getMessage());
        }
    }

    private static void addItemToOrder() {
        String orderNumber = getStringInput("Enter order number: ");
        String itemName = getStringInput("Enter item name: ");
        int quantity = getIntInput("Enter quantity: ");

        try {
            system.addItemToOrder(orderNumber, itemName, quantity);
            System.out.println("Item added to order successfully.");
        } catch (Exception e) {
            System.out.println("Error adding item to order: " + e.getMessage());
        }
    }

    private static void setOrderPaymentMethod() {
        String orderNumber = getStringInput("Enter order number: ");
        System.out.println("Select payment method:");
        System.out.println("1. Bank Transfer");
        System.out.println("2. Card");
        System.out.println("3. Mobile Payment");
        System.out.println("4. Cash");

        int choice = getIntInput("Enter choice: ");
        Order.PaymentMethod paymentMethod;

        switch (choice) {
            case 1:
                paymentMethod = Order.PaymentMethod.BANK_TRANSFER;
                break;
            case 2:
                paymentMethod = Order.PaymentMethod.CARD;
                break;
            case 3:
                paymentMethod = Order.PaymentMethod.MOBILE_PAYMENT;
                break;
            case 4:
                paymentMethod = Order.PaymentMethod.CASH;
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        system.setOrderPaymentMethod(orderNumber, paymentMethod);
        System.out.println("Payment method set successfully.");
    }

    private static void completeOrder() {
        String orderNumber = getStringInput("Enter order number: ");
        system.completeOrder(orderNumber);
        System.out.println("Order completed successfully.");
    }

    private static void viewOrderBill() {
        String orderNumber = getStringInput("Enter order number: ");
        String bill = system.generateOrderBill(orderNumber);
        System.out.println(bill);
    }
} 