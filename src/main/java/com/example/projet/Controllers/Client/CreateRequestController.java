package com.example.projet.Controllers.Client;

import com.example.projet.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateRequestController implements Initializable {
    public TextField fNameRequest_fld;
    public TextField lNameRequest_fld;
    public TextField requestProgrName_fld;
    public CheckBox numRequest_box;
    public Label numRequest_lbl;
    public Button create_request_btn;
    public Label error_lbl;
    public DatePicker commissioning_date;

    private String number;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_request_btn.setOnAction(event -> createRequest());
        numRequest_box.selectedProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal) {
                number = createNumber();
                onCreateNumber();
            }
        });
    }

    private void createRequest() {
        // Create Client
        String fName = fNameRequest_fld.getText();
        String lName = lNameRequest_fld.getText();
        String message = requestProgrName_fld.getText();
        Model.getInstance().getDatabaseDriver().createRequest(fName, lName, number, LocalDate.now(), message);
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold;");
        error_lbl.setText("Client created Successfully!");
        emptyFields();
    }

    private void onCreateNumber(){
        if (fNameRequest_fld.getText() != null && lNameRequest_fld.getText() != null) {
            numRequest_lbl.setText(number);
        }
    }

    private String createNumber(){
        int id = Model.getInstance().getDatabaseDriver().getLastNumberId() + 1;
        char fChar = Character.toLowerCase(fNameRequest_fld.getText().charAt(0));
        return fChar+lNameRequest_fld.getText()+id;
    }

    private void emptyFields(){
        fNameRequest_fld.setText("");
        lNameRequest_fld.setText("");
        requestProgrName_fld.setText("");
        numRequest_box.setSelected(false);
        numRequest_lbl.setText("");
    }
}


