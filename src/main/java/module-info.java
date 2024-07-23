module com.example.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    // requires org.controlsfx.controls;
   // requires com.dlsc.formsfx;

    opens com.example.projet to javafx.fxml;
    opens com.example.projet.Controllers.Client to javafx.fxml;

    exports com.example.projet;
    exports com.example.projet.Controllers;
    exports com.example.projet.Controllers.Admin;
    exports com.example.projet.Controllers.Client;
    exports com.example.projet.Models;
    exports com.example.projet.Views;
}