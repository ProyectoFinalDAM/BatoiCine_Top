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
import es.cipfpbatoi.utils.URLChecker;
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
    private ImageView estrella1;
    @FXML
    private ImageView estrella2;
    @FXML
    private ImageView estrella3;
    @FXML
    private ImageView estrella4;
    @FXML
    private ImageView estrella5;


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

    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository, Initializable controllerAnterior, String vista, User user) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
        this.esFavoritaRepository = new EsFavoritaRepository(new SQLEsFavoritaDAO(), produccionRepository, new UserRepository(new SQLUserDAO()));
        this.user = user;
        this.controllerAnterior= controllerAnterior;
        this.vista= vista;
    }

    @FXML
    private void encender1(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }
    @FXML
    private void encender2(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }
    @FXML
    private void encender3(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }
    @FXML
    private void encender4(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }
    @FXML
    private void encender5(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
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
        private void verMasTarde(MouseEvent event) throws DatabaseErrorException, NotFoundException, URISyntaxException {
            if (esFavorita()){
                esFavoritaRepository.eliminar(user, produccion);
                actualizarEsFavorita(false);
            }else {
            esFavoritaRepository.save(user, produccion);
            actualizarEsFavorita(true);
            }
        }

        private boolean esFavorita()throws DatabaseErrorException, NotFoundException{
            for (Produccion produccion1:esFavoritaRepository.getUserFavs(this.user)) {
                if (produccion1.getId().equals(this.produccion.getId())){
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

                if ( URLChecker.checkURL( produccion.getPoster() ) ) {
                    portada.setImage(new Image(produccion.getPoster()));
                } else {
                    portada.setImage( new Image( getPathImage( "/images/default.png" ) ) );
                }
                actualizarEsFavorita(esFavorita());
                estrella1.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
                estrella2.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
                estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
                estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
                estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            } catch (URISyntaxException | DatabaseErrorException | NotFoundException e) {
                throw new RuntimeException(e);
            }


            descripcion.setText(produccion.getGuion());
            descripcion.setWrapText(true);
            descripcion.setPrefWidth(370);



        }

        private String getPathImage (String fileName) throws URISyntaxException {
            return getClass().getResource(fileName).toURI().toString();
        }
}
