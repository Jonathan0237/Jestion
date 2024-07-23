package com.example.projet.Views;

import com.example.projet.Controllers.Admin.AdminController;
import com.example.projet.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;

    //Client View
    private  final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;
    private AnchorPane createRequestView;
    private AnchorPane requestView;
    private AnchorPane profileView;

    //Admin View
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane dashboardAdminView;
    private AnchorPane createClientView;
    private AnchorPane clientsView;
    private AnchorPane depositView;

    public ViewFactory(){
        this.loginAccountType = AccountType.Admin;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
    * Client Views Section
    */

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getCreateRequestView() {
        if (createRequestView == null) {
            try {
                createRequestView = new FXMLLoader(getClass().getResource("/Fxml/Client/CreateRequest.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return createRequestView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getRequestView() {
        if (requestView == null) {
            try {
                requestView = new FXMLLoader(getClass().getResource("/Fxml/Client/Request.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requestView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public AnchorPane getProfileView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(getClass().getResource("/Fxml/Client/Profile.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileView;
    }

    public void  showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    /*
    * Admin Views Section
    */

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getDashboardAdminView() {
        if (dashboardAdminView == null) {
            try {
                dashboardAdminView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Dashboard.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardAdminView;
    }

    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getDepositView() {
        if (depositView == null) {
            try {
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public void  showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showMessageWindow(String senderAddress, String messageText) {
        StackPane pane = new StackPane();
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        Label sender = new Label(senderAddress);
        Label message = new Label(messageText);
        hbox.getChildren().addAll(sender, message);
        pane.getChildren().add(hbox);
        Scene scene = new Scene(pane, 600, 350);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Project");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
