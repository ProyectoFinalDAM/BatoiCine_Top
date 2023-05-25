module es.cipfpbatoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens es.cipfpbatoi.controllers to javafx.fxml;
    exports es.cipfpbatoi;
}
