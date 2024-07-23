package com.example.projet.Controllers.Client;

import com.example.projet.Models.Client;
import com.example.projet.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label payeeAddressLabel;
    @FXML
    private Label dateCreatedLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button printButton;

    private Client client;

    public ProfileController() {}

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (client != null) {
            bindClientProperties();
            setupButtonActions();
        } else {
            System.out.println("Client is null.");
        }
    }

    private void bindClientProperties() {
        try {
            firstNameLabel.textProperty().bind(client.firstNameProperty());
            lastNameLabel.textProperty().bind(client.lastNameProperty());
            payeeAddressLabel.textProperty().bind(client.pAddressProperty());
            dateCreatedLabel.textProperty().bind(client.dateProperty().asString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupButtonActions() {
        printButton.setOnAction(e -> printClientProfile());
        editButton.setOnAction(e -> showEditClientDialog());
    }

    private void printClientProfile() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            VBox vbox = new VBox();
            vbox.getChildren().add(new Text("FirstName: " + client.firstNameProperty().get()));
            vbox.getChildren().add(new Text("LastName: " + client.lastNameProperty().get()));
            vbox.getChildren().add(new Text("Email: " + client.pAddressProperty().get()));
            vbox.getChildren().add(new Text("Date de Création: " + client.dateProperty().get().toString()));

            Scene scene = new Scene(vbox);
            job.getJobSettings().setPageLayout(Printer.getDefaultPrinter().getDefaultPageLayout());

            if (job.showPrintDialog(firstNameLabel.getScene().getWindow())) {
                boolean success = job.printPage(scene.getRoot());
                if (success) {
                    job.endJob();
                }
            }
        }
    }

    private void showEditClientDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        TextField firstNameField = new TextField(client.firstNameProperty().get());
        TextField lastNameField = new TextField(client.lastNameProperty().get());
        TextField emailField = new TextField(client.pAddressProperty().get());
        Button saveButton = new Button("Save");

        saveButton.setOnAction(e -> {
            Model.getInstance().getDatabaseDriver().updateClient(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText()
            );
            client.firstNameProperty().set(firstNameField.getText());
            client.lastNameProperty().set(lastNameField.getText());
            client.pAddressProperty().set(emailField.getText());
            stage.close();
        });

        vbox.getChildren().addAll(new Label("FirstName:"), firstNameField, new Label("LastName:"), lastNameField, new Label("Email:"), emailField, saveButton);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Update User");
        stage.show();
    }
}
