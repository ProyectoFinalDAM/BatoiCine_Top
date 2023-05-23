module es.cipfpbatoi {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.cipfpbatoi to javafx.fxml;
    exports es.cipfpbatoi;
}
