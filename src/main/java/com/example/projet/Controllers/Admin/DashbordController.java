package com.example.projet.Controllers.Admin;

import com.example.projet.Models.Model;
import com.example.projet.Models.Request;
import com.example.projet.Views.RequestCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashbordController implements Initializable {
        public Text user_name;
        public Label login_date;
        public ListView<Request> transaction_listview;
        public AreaChart<String, Number> userDay;
        public BarChart<String, Number> requestDay;
        public Circle circle;

    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            bindData();
            initLatestTransactionsList();
            transaction_listview.setItems(Model.getInstance().getRequests());
            transaction_listview.setCellFactory(e -> new RequestCellFactory());

        // Initialiser les graphiques
        initializeUserDayChart();
        initializeRequestDayChart();
        }

        private void bindData() {
            user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getAdmin().usernameProperty()));
            login_date.setText("Today, " + LocalDate.now());
        }

        private void initLatestTransactionsList() {
            if (Model.getInstance().getRequests().isEmpty()) {
                Model.getInstance().setRequests();
            }
        }

    private void initializeUserDayChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("User Registrations");

        // Exemple de données. Remplacez ceci par vos données réelles.
        series.getData().add(new XYChart.Data<>("January", 200));
        series.getData().add(new XYChart.Data<>("February", 150));
        series.getData().add(new XYChart.Data<>("March", 300));
        series.getData().add(new XYChart.Data<>("April", 250));
        series.getData().add(new XYChart.Data<>("May", 500));
        series.getData().add(new XYChart.Data<>("June", 450));
        series.getData().add(new XYChart.Data<>("July", 700));
        series.getData().add(new XYChart.Data<>("August", 650));
        series.getData().add(new XYChart.Data<>("September", 400));
        series.getData().add(new XYChart.Data<>("October", 350));
        series.getData().add(new XYChart.Data<>("November", 300));
        series.getData().add(new XYChart.Data<>("December", 500));

        userDay.getData().add(series);
    }

    private void initializeRequestDayChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Requests");

        // Exemple de données. Remplacez ceci par vos données réelles.
        series.getData().add(new XYChart.Data<>("Week 1", 50));
        series.getData().add(new XYChart.Data<>("Week 2", 80));
        series.getData().add(new XYChart.Data<>("Week 3", 45));
        series.getData().add(new XYChart.Data<>("Week 4", 60));
        series.getData().add(new XYChart.Data<>("Week 5", 90));
        series.getData().add(new XYChart.Data<>("Week 6", 70));
        series.getData().add(new XYChart.Data<>("Week 7", 100));
        series.getData().add(new XYChart.Data<>("Week 8", 55));

        requestDay.getData().add(series);
    }
}
