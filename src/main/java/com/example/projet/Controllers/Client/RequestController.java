package com.example.projet.Controllers.Client;

import com.example.projet.Models.Model;
import com.example.projet.Models.Request;
import com.example.projet.Views.RequestCellFactory;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestController implements Initializable {
    public ListView<Request> request_listview;
    public Button print;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllRequestList();
        request_listview.setItems(Model.getInstance().getRequests());
        request_listview.setCellFactory(e -> new RequestCellFactory());

        print.setOnAction(e -> printRequestList());
    }

    private void initAllRequestList() {
        if(Model.getInstance().getRequests().isEmpty()) {
            Model.getInstance().setRequests();
        }
    }

    private void printRequestList() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            VBox vbox = new VBox();
            for (Request request : request_listview.getItems()) {
                vbox.getChildren().add(new Text(request.toString()));
            }

            Scene scene = new Scene(vbox);
            job.getJobSettings().setPageLayout(Printer.getDefaultPrinter().getDefaultPageLayout());

            if (job.showPrintDialog(request_listview.getScene().getWindow())) {
                boolean success = job.printPage(scene.getRoot());
                if (success) {
                    job.endJob();
                }
            }
        }
    }

}
