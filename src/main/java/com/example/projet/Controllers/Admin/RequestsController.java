package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Model;
import com.example.projet.Models.Request;
import com.example.projet.Views.RequestCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestsController implements Initializable {

    public ListView<Request> request_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllRequestList();
        request_listview.setItems(Model.getInstance().getRequests());
        request_listview.setCellFactory(e -> new RequestCellFactory());
    }

    private void initAllRequestList() {
        if(Model.getInstance().getRequests().isEmpty()) {
            Model.getInstance().setRequests();
        }
    }
}
