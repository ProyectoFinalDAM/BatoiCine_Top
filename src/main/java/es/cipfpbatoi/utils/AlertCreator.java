package es.cipfpbatoi.utils;

import javafx.scene.control.Alert;

/**
 * Esta clase contiende metodos para mostrar ventanas emergentes en forma de alerta para mostrar error o infomración
 */

public class AlertCreator {

    /**
     * @author Marcos Sanza
     * @param message el parametro message es un texto de alerta
     */

    public static void errorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     @author Marcos Sanza
     @param message el parametro message es un texto de alerta
     */

    public static void infoAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
