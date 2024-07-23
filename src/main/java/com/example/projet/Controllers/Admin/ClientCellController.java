package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Client;
import com.example.projet.Models.Model;
import com.example.projet.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {

    public Label fName_lbl;
    public Label pAddress_lbl;
    public Label lName_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.textProperty().bind(client.firstNameProperty());
        lName_lbl.textProperty().bind(client.lastNameProperty());
        pAddress_lbl.textProperty().bind(client.pAddressProperty());
        date_lbl.textProperty().bind(client.dateProperty().asString());
        delete_btn.setOnAction(e -> {
            String email = client.getEmail();
            Model.getInstance().getDatabaseDriver().deleteClient(email);
        });
    }
}
