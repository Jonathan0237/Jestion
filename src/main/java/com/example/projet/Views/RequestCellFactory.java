package com.example.projet.Views;

import com.example.projet.Controllers.Client.RequestCellController;
import com.example.projet.Models.Request;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class RequestCellFactory extends ListCell<Request> {
    @Override
    protected void updateItem(Request request, boolean empty) {
        super.updateItem(request, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/RequestCell.fxml"));
            RequestCellController controller = new RequestCellController(request);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
