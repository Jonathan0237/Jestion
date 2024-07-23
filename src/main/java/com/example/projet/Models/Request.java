package com.example.projet.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Request {

    private final StringProperty sender;
    private final StringProperty receiver;
    private final StringProperty numRequest;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    public Request(String sender, String receiver, String numRequest, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(this, "Sender", sender);
        this.receiver = new SimpleStringProperty(this, "Receiver", receiver);
        this.numRequest = new SimpleStringProperty(this, "num", numRequest);
        this.date = new SimpleObjectProperty<>(this,"Date", date);
        this.message = new SimpleStringProperty(this, "Message", message);
    }

    public StringProperty senderProperty() {
        return this.sender;
    }

    public StringProperty receiverProperty() {
        return this.receiver;
    }

    public StringProperty numRequestProperty() {
        return this.numRequest;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    public StringProperty messageProperty() {
        return this.message;
    }

}
