package es.cipfpbatoi.controllers;

import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import es.cipfpbatoi.models.respositories.*;
import javafx.beans.binding.Bindings;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


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
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private Produccion produccion;
    private EsFavoritaRepository esFavoritaRepository;
    private User user;


    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository, EsFavoritaRepository esFavoritaRepository, User user) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
        this.esFavoritaRepository = esFavoritaRepository;
        this.user = user;
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

    @FXML
    private void verMasTarde(ActionEvent event) {
        esFavoritaRepository.save(user, produccion);
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
    }

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
