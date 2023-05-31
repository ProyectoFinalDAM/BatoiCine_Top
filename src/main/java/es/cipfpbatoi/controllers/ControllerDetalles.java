package es.cipfpbatoi.controllers;

import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    private TextField descripcion;
    @FXML
    private ImageView portada;

    private ImageView logoImageView;
    @FXML
    private ImageView flecha;
    @FXML

    private ProduccionRepository produccionRepository;
   

    private ValoracionDAO valoracionDAO;
    private RankingDAO rankingDAO;
    private Produccion produccion;

    public ControllerDetalles(ValoracionDAO valoracionDAO, RankingDAO rankingDAO, Produccion produccion, ProduccionRepository produccionRepository) {
        this.valoracionDAO = valoracionDAO;
        this.rankingDAO = rankingDAO;
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
            portada.setImage(new Image(produccion.getPoster()));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
