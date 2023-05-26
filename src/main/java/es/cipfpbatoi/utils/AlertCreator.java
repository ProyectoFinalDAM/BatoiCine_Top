package es.cipfpbatoi.utils;

import javafx.scene.control.Alert;

public class AlertCreator {
    public static void errorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void infoAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
