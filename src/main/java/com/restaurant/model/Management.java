package com.restaurant.model;

public class Management extends Staff {
    private String department;
    private String accessLevel;

    public Management(String name, String id, String department) {
        super(name, id, "Management");
        this.department = department;
        this.accessLevel = "ADMIN";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    @Override
    public String toString() {
        return "Management{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", department='" + department + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                '}';
    }
} 