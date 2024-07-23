package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Client;
import com.example.projet.Models.Model;
import com.example.projet.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {

    public TextField pAddress_fld;
    public Button search_btn;
    public ListView<Client> result_listview;
//    public TextField amount_fld;
//    public Button deposit_btn;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(e -> onClientSearch());
//        deposit_btn.setOnAction(e -> onDeposit());
    }

    private void onClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClient(pAddress_fld.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.get(0);
    }

/*    public void onDeposit(){
        double amount = Double.parseDouble(amount_fld.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if (amount_fld.getText() != null){
            Model.getInstance().getDatabaseDriver().depositSavings(client.pAddressProperty().get(), newBalance);
        }
        emptyFields();
    }

    private void emptyFields(){
        pAddress_fld.setText("");
        amount_fld.setText("");
    }
 */
}
