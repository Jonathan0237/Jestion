package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Model;
import com.example.projet.Views.AdminMenuOptions;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((ObservableValue, oldValue, newValue) -> {
            switch (newValue) {
                case AdminMenuOptions.CREATE_CLIENT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
                case AdminMenuOptions.CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case AdminMenuOptions.REQUESTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getRequestView());
                case AdminMenuOptions.DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDashboardAdminView());
            }
        });
    }
}
