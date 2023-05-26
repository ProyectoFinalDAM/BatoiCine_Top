package es.cipfpbatoi;

import es.cipfpbatoi.controllers.ChangeScene;
import es.cipfpbatoi.controllers.LoginController;
import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.file.FileGeneroDAO;
import es.cipfpbatoi.models.dao.file.FileProduccionDAO;
import es.cipfpbatoi.models.dao.sql.SQLGeneroDAO;
import es.cipfpbatoi.models.dao.sql.SQLProduccionDAO;
import es.cipfpbatoi.models.dao.sql.SQLUserDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX es.cipfpbatoi.App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        LoginController loginController= new LoginController();
        SQLProduccionDAO sqlProduccionDAO= new SQLProduccionDAO();
        FileProduccionDAO fileProduccionDAO= new FileProduccionDAO();

        for (Produccion produccion: fileProduccionDAO.findAll()) {
            try {
                sqlProduccionDAO.save(produccion);
            } catch (DatabaseErrorException e) {
                throw new RuntimeException(e);
            }
        }

        ChangeScene.change(stage, loginController, "/views/login.fxml");

    }

    public static void main(String[] args) {
        launch();
    }

}