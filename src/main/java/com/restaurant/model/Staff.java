package com.restaurant.model;

public class Staff extends User {
    private double workingHours;
    private String role;

    public Staff(String name, String id, String role) {
        super(name, id);
        this.role = role;
        this.workingHours = 0;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void addWorkingHours(double hours) {
        if (hours > 0) {
            this.workingHours += hours;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", role='" + role + '\'' +
                ", workingHours=" + workingHours +
                '}';
    }
} 