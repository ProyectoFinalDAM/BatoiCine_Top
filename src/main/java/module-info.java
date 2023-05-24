module es.cipfpbatoi {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.cipfpbatoi.controllers to javafx.fxml;
    exports es.cipfpbatoi;
    exports es.cipfpbatoi.models;
    exports;
}
