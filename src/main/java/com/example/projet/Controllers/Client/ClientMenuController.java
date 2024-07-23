package com.example.projet.Controllers.Client;

import com.example.projet.Models.Model;
import com.example.projet.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;
    public Button transaction_btn;
    public Button request_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
//        dashboard_btn.setOnAction(event -> onDashboard());
        request_btn.setOnAction(event -> onCreateRequest());
        transaction_btn.setOnAction(event -> onTransactions());
        logout_btn.setOnAction(event -> onLogout());
        profile_btn.setOnAction(event -> onProfile());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.REQUESTS);
    }

    private void onCreateRequest() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.CREATE_REQUEST);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }

    private void onRequest() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.REQUESTS);
    }

/*    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }
*/
    private void onLogout() {
        // Get stage
        Stage stage = (Stage) profile_btn.getScene().getWindow();
        // Close the Client Window
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Client Login Success Flag to false
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}
