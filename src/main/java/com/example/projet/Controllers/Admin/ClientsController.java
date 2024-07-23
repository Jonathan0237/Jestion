package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Client;
import com.example.projet.Models.Model;
import com.example.projet.Models.Request;
import com.example.projet.Views.ClientCellFactory;
import com.example.projet.Views.RequestCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    private void initClientsList(){
         if (Model.getInstance().getClients().isEmpty()){
            Model.getInstance().setClients();
        }
    }
}

