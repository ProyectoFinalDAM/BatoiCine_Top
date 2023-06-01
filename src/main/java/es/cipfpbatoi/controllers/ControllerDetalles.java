package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dao.sql.SQLEsFavoritaDAO;
import es.cipfpbatoi.models.dao.sql.SQLUserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Estrella;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import es.cipfpbatoi.models.respositories.*;
import javafx.beans.binding.Bindings;

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
import javafx.scene.input.MouseEvent;
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
    private Label descripcion;
    @FXML
    private ImageView portada;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView flecha;

    @FXML
    private ImageView corazon;
    @FXML
    private HBox container;

    private static final int NUM_ESTRELLAS = 5;
    private static final Color ESTRELLA_ENCENDIDA_COLOR = Color.GOLD;
    private static final Color ESTRELLA_APAGADA_COLOR = Color.LIGHTGRAY;

    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private Produccion produccion;
    private EsFavoritaRepository esFavoritaRepository;
    private User user;

    private Initializable controllerAnterior;

    private String vista;



    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository, Initializable controllerAnterior, String vista,  User user) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
        this.esFavoritaRepository = new EsFavoritaRepository(new SQLEsFavoritaDAO(), produccionRepository, new UserRepository(new SQLUserDAO()));
        this.user = user;
    }


    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository, Initializable controllerAnterior, String vista) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
        this.controllerAnterior= controllerAnterior;
        this.vista= vista;
    }

        //Método para salir de la vista de detalles y volver a la principal
        @FXML
        private void haciaAtras (MouseEvent event){
            try {
                ChangeScene.change(event, controllerAnterior, vista);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @FXML
        private void verMasTarde(ActionEvent event) throws DatabaseErrorException, NotFoundException {
            if (esFavorita()){
                esFavoritaRepository.eliminar(user, produccion);
            }else {
            esFavoritaRepository.save(user, produccion);
            }
        }

        private boolean esFavorita()throws DatabaseErrorException, NotFoundException{
            for (Produccion produccion1:esFavoritaRepository.getUserFavs(this.user)) {
                if (produccion1.equals(this.produccion)){
                    return true;
                }
            }
            return false;
        }

        private void actualizarEsFavorita(boolean favorita) throws URISyntaxException {
            if (favorita){
                corazon.setImage(new Image(getPathImage("/images/CorazonColor.png")));
            }else {
                corazon.setImage(new Image(getPathImage("/images/corazonBlancoyNegro.png")));
            }
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            try {
                logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
                flecha.setImage(new Image(getPathImage("/images/Flecha_goBack.png")));
                portada.setImage(new Image(produccion.getPoster()));
                actualizarEsFavorita(esFavorita());

            } catch (URISyntaxException | DatabaseErrorException | NotFoundException e) {
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

        private String getPathImage (String fileName) throws URISyntaxException {
            return getClass().getResource(fileName).toURI().toString();
        }
}
