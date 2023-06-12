package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.UserNotExistException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {

    /**
     * Nos sirve para poder cambiar de vista con stage
     * @author Marcos Sanz
     * @param stage, controller, pathToViewFile
     */

    public static void change(Stage stage, Initializable controller, String pathToViewFile) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ChangeScene.class.getResource(pathToViewFile));
        fxmlLoader.setController(controller);

        AnchorPane rootLayout = fxmlLoader.load();

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Nos sirve para poder cambiar de vista, pero con esta vez un parametro de event en vez de stage
     * @author Pablo Marin
     * @param event, controller, pathToViewFile
     */

    public static void change(Event event, Initializable controller,
                              String path_to_view_file) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        change(stage, controller, path_to_view_file);
    }
}