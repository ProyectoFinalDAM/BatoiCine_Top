package es.cipfpbatoi;

import es.cipfpbatoi.controllers.ChangeScene;
import es.cipfpbatoi.controllers.ControllerDetalles;
import es.cipfpbatoi.controllers.LoginController;
import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.file.FileGeneroDAO;
import es.cipfpbatoi.models.dao.file.FileProduccionDAO;
import es.cipfpbatoi.models.dao.sql.*;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.UserRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX es.cipfpbatoi.App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, DatabaseErrorException {
        SQLUserDAO sqlUserDAO = new SQLUserDAO();
//        UserRepository userRepository= new UserRepository(sqlUserDAO);
//        LoginController loginController= new LoginController(userRepository);
        SQLValoracionDAO sqlValoracionDAO = new SQLValoracionDAO();
        SQLRankingDAO sqlRankingDAO = new SQLRankingDAO();
        RankingRepository rankingRepository = new RankingRepository(sqlRankingDAO);
        ValoracionRepository valoracionRepository = new ValoracionRepository(sqlValoracionDAO);
        ArrayList<Produccion> produccions = new SQLProduccionDAO().findAll();
        ControllerDetalles controllerDetalles = new ControllerDetalles(valoracionRepository, rankingRepository, produccions.get(0));

        ChangeScene.change(stage, controllerDetalles, "/views/detail_production.fxml");

    }

    public static void main(String[] args) {
        launch();
    }

}

