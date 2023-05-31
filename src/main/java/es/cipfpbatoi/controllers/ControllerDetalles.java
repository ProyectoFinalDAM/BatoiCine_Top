package es.cipfpbatoi.controllers;

import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Estrella;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import javafx.beans.binding.Bindings;
import es.cipfpbatoi.models.respositories.ProduccionRepository;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDetalles implements Initializable {

    @FXML
    private Button uno;
    @FXML
    private Button dos;
    @FXML
    private Button tres;
    @FXML
    private Button cuatro;
    @FXML
    private Button cinco;
    @FXML
    private Button verifiar;
    @FXML
    private Label descripcion;
    @FXML
    private ImageView portada;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView flecha;

    @FXML
    private HBox container;

    private static final int NUM_ESTRELLAS = 5;
    private static final Color ESTRELLA_ENCENDIDA_COLOR = Color.GOLD;
    private static final Color ESTRELLA_APAGADA_COLOR = Color.LIGHTGRAY;

    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private Produccion produccion;

    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
    }


    //Método para salir de la vista de detalles y volver a la principal
    @FXML
    private void haciaAtras(ActionEvent event) {

        try {
            MainController mainController = new MainController(produccionRepository);
            ChangeScene.change(event, mainController, "/views/main.fxml");
        } catch ( IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
            flecha.setImage(new Image(getPathImage("/images/Flecha_goBack.png")));
            portada.setImage(new Image (produccion.getPoster()));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        descripcion.setText(produccion.getGuion());
        descripcion.setWrapText(true);
        descripcion.setPrefWidth(370);

        for (int i = 0; i < NUM_ESTRELLAS; i++) {
            Estrella estrella = new Estrella(i);
            container.getChildren().add(estrella);
        }

    }

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
