package com.example.projet.Controllers.Client;

import com.example.projet.Models.Model;
import com.example.projet.Models.Request;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestCellController implements Initializable {

    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Button message_btn;
    public Label numRequest_lbl;

    private final Request request;

    public RequestCellController(Request request) {
        this.request = request;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sender_lbl.textProperty().bind(request.senderProperty());
        receiver_lbl.textProperty().bind(request.receiverProperty());
        numRequest_lbl.textProperty().bind(request.numRequestProperty());
        trans_date_lbl.textProperty().bind(request.dateProperty().asString());
        message_btn.setOnAction(event -> Model.getInstance().getViewFactory().showMessageWindow(request.senderProperty().get(), request.messageProperty().get()));
    }
}
