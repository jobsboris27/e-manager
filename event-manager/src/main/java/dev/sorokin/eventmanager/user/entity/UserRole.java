package dev.sorokin.eventmanager.user.entity;

public enum UserRole {
    USER,
    ADMIN;
    public String authority() {
        return "ROLE_" + name();
    }
}