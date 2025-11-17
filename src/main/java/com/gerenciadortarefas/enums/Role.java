package com.gerenciadortarefas.enums;

public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}