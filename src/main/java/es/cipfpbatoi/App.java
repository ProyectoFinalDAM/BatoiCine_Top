package es.cipfpbatoi;

import es.cipfpbatoi.controllers.ChangeScene;
import es.cipfpbatoi.controllers.ControllerDetalles;
import es.cipfpbatoi.controllers.LoginController;
import es.cipfpbatoi.controllers.MainController;
import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.EsFavoritaDAO;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.file.FileActuaDAO;
import es.cipfpbatoi.models.dao.file.FileGeneroDAO;
import es.cipfpbatoi.models.dao.file.FileProduccionDAO;
import es.cipfpbatoi.models.dao.file.FileTemporadaDAO;
import es.cipfpbatoi.models.dao.sql.*;
import es.cipfpbatoi.models.dto.prods.*;
import es.cipfpbatoi.models.respositories.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX es.cipfpbatoi.App
 */
public class App extends Application {

    private static Scene scene;

    public static final Color ESTRELLA_ENCENDIDA_COLOR = Color.GOLD;
    public static final Color ESTRELLA_APAGADA_COLOR = Color.LIGHTGRAY;

    @Override
    public void start(Stage stage) throws IOException, DatabaseErrorException {
        SQLUserDAO sqlUserDAO = new SQLUserDAO();
        UserRepository userRepository= new UserRepository(sqlUserDAO);

        SQLProduccionDAO sqlProduccionDAO = new SQLProduccionDAO();
        SQLEsFavoritaDAO sqlEsFavoritaDAO = new SQLEsFavoritaDAO();
        SQLValoracionDAO sqlValoracionDAO = new SQLValoracionDAO();
        SQLRankingDAO sqlRankingDAO = new SQLRankingDAO();
        RankingRepository rankingRepository = new RankingRepository(sqlRankingDAO);
        ValoracionRepository valoracionRepository = new ValoracionRepository(sqlValoracionDAO);
        SQLVisualizarDAO sqlVisualizarDAO= new SQLVisualizarDAO();
        ProduccionRepository produccionRepository = new ProduccionRepository(sqlProduccionDAO);
        SQLGeneroDAO sqlGeneroDAO= new SQLGeneroDAO();
        GeneroRepository generoRepository= new GeneroRepository(sqlGeneroDAO);
        VisualizarRepository visualizarRepository= new VisualizarRepository(sqlVisualizarDAO, produccionRepository, userRepository);
        EsFavoritaRepository esFavoritaRepository= new EsFavoritaRepository(sqlEsFavoritaDAO, produccionRepository, userRepository);
        LoginController loginController= new LoginController(userRepository, produccionRepository, generoRepository,valoracionRepository, rankingRepository, visualizarRepository, esFavoritaRepository);
        ChangeScene.change(stage, loginController, "/views/login.fxml");

    }

    public static void main(String[] args) {
        launch();
    }

}

