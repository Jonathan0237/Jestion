package com.example.projet.Controllers.Client;

import com.example.projet.Models.Model;
import com.example.projet.Views.AdminMenuOptions;
import com.example.projet.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case REQUESTS -> client_parent.setCenter(Model.getInstance().getViewFactory().getRequestView());
                case CREATE_REQUEST -> client_parent.setCenter(Model.getInstance().getViewFactory().getCreateRequestView());
                //case REQUESTS -> client_parent.setCenter(Model.getInstance().getViewFactory().getRequestView());
                case ACCOUNTS -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                //case PROFILE -> client_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
            }
        });
    }
}
