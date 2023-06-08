package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.ActorDAO;
import es.cipfpbatoi.models.dao.ActuaDAO;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dao.sql.SQLEsFavoritaDAO;
import es.cipfpbatoi.models.dao.sql.SQLUserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Actor;
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
import javafx.scene.control.Alert;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerDetalles implements Initializable {

    @FXML
    private Label descripcion;
    @FXML
    private Label directores;
    @FXML
    private Label titulo;
    @FXML
    private Label plataformas;
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
    @FXML
    private TextField opinionComentario;


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
        this.controllerAnterior = controllerAnterior;
        this.vista = vista;
    }


    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 1 estrella, se encenderá 1 solamente 1 estrella (cambiará la imagen).
     * @author Andreu Francés
     * @param event
     */

    @FXML
    private void encender1(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 2 estrellas, se encenderan la misma y la anterior, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */

    @FXML
    private void encender2(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 3 estrellas, se encenderan la misma y las anteriores, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */

    @FXML
    private void encender3(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 4 estrellas, se encenderan la misma y las anteriores, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */


    @FXML
    private void encender4(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 5 estrellas, se encenderan la misma y las anteriores
     * es decir, todas las posibles (cambiando la imagen de todas las estrellas).
     * @author Andreu Francés
     * @param event
     */

    @FXML
    private void encender5(MouseEvent event) throws URISyntaxException {
        estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        estrella5.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
    }

    /**
     * Para que cuando estemos en esta vista y le demos atrás, se encarga de llevarnos al controlador Main
     * @author Pablo Marin
     * @param event
     */

    //Método para salir de la vista de detalles y volver a la principal
    @FXML
    private void haciaAtras(MouseEvent event) {
        try {
            ChangeScene.change(event, controllerAnterior, vista);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cuando nos encontremos dentro de una producción y queramos guardarla, este método se encarga de guardarla para que mas tarde la podamos ver
     * y en caso de ya estar, eliminarla de ver mas tarde y de favoritas (también actualiza su imagen).
     * @author Andreu Francés
     * @author Pablo Marín
     * @param event
     */

    @FXML
    private void verMasTarde(MouseEvent event) throws DatabaseErrorException, NotFoundException, URISyntaxException {
        if (esFavorita()) {
            esFavoritaRepository.eliminar(user, produccion);
            actualizarEsFavorita(false);
        } else {
            esFavoritaRepository.save(user, produccion);
            actualizarEsFavorita(true);
        }
    }

    /**
     * Recorremos todas las producciones con el usuario ya registrado.
     * @author Andreu Francés
     * @return si la produccion ya está como favorita retorna true
     * @return si la produccion no está como favorita retorna false
     */

    private boolean esFavorita() throws DatabaseErrorException, NotFoundException {
        for (Produccion produccion1 : esFavoritaRepository.getUserFavs(this.user)) {
            if (produccion1.getId().equals(this.produccion.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Se encarga de actualizar la imagen de la produccion dependiendo de si favorita o sino lo es.
     * @author Andreu Francés
     */

    private void actualizarEsFavorita(boolean favorita) throws URISyntaxException {
        if (favorita) {
            corazon.setImage(new Image(getPathImage("/images/CorazonColor.png")));
        } else {
            corazon.setImage(new Image(getPathImage("/images/corazonBlancoyNegro.png")));
        }
    }

    /**
     * Se encarga de recoger el comentario introducido a la hora de valorar una pelicula
     * @author Pablo Marin
     * @return String , recogido del TextField del comentario del usuario sobre la producción
     */

    @FXML
    private String recogerComentario(ActionEvent event) {
        
        String comentarioProduccion = null;

            if (isValoracionValida()) {
                comentarioProduccion = opinionComentario.getText();
        }
            return comentarioProduccion;
    }

    /**
     * Se encarga de recoger validar si la Valoracion del usuario es valida (no puede comentar en la produccion sin haberla valorado).
     * @author Pablo Marin
     * @return boolean , dependiendo de si la valoranción es o no es valida.
     */

    private boolean isValoracionValida() {
        if (estrellasVacias() && !opinionComentario.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle( "Debes introducir una valoracion para poder añadir un comentario" );
            alert.setHeaderText( "" );
            alert.setContentText( "Atención, para poder añadir un comentario, debes previamente de haber valorado la producción " );
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Se encarga de comprobar si las 5 estrellas disponibles para valorar una pelicula están todas vacías.
     * @author Pablo Marin
     * @return boolean , dependiendo de si la producción ya ha sido valorada o no.
     */

    private boolean estrellasVacias() {
        String rutaImagenEspecifica = "/images/EstrellaBlanca.png";

        if (estrella1.getImage().getUrl().equals(rutaImagenEspecifica)
                && estrella2.getImage().getUrl().equals(rutaImagenEspecifica)
                && estrella3.getImage().getUrl().equals(rutaImagenEspecifica)
                && estrella4.getImage().getUrl().equals(rutaImagenEspecifica)
                && estrella5.getImage().getUrl().equals(rutaImagenEspecifica)) {

            return true;
        }

        return false;
    }

    /**
     * Inicializamos todas las imágenes para que cuando cargue el controlador ya estén definidas, y también nos encargamos de
     * insertar la descripción de la producción junto a las plataformas en las que está disponible y sus actores.
     * @author Pablo Marin
     * @return boolean , dependiendo de si la producción ya ha sido valorada o no.
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
            flecha.setImage(new Image(getPathImage("/images/Flecha_goBack.png")));

            if (URLChecker.checkURL(produccion.getPoster())) {
                portada.setImage(new Image(produccion.getPoster()));
            } else {
                portada.setImage(new Image(getPathImage("/images/default.png")));
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
        directores.setText( produccion.getDirector() );
        plataformas.setText( produccion.getPlataformasFormat() );
        titulo.setText( produccion.getTitulo() );

    }

    /**
     * Para poder cargar las imagenes locales mediante su url
     * @author Pablo Marin
     * @return String , la ruta de dicha imagen.
     */

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
