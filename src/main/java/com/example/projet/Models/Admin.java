package com.example.projet.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    private final StringProperty username;

    public Admin(String username) {
        this.username = new SimpleStringProperty(this, "Username", username);
    }

    public StringProperty usernameProperty() {
        return username;
    }
}
