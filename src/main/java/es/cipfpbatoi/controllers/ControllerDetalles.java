package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.sql.SQLEsFavoritaDAO;
import es.cipfpbatoi.models.dao.sql.SQLUserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import es.cipfpbatoi.models.dto.prods.Valoracion;
import es.cipfpbatoi.models.respositories.*;
import es.cipfpbatoi.utils.URLChecker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


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
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private Produccion produccion;
    private EsFavoritaRepository esFavoritaRepository;
    private User user;
    private Initializable controllerAnterior;

    private String vista;
    private int valorar;

    public ControllerDetalles(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, Produccion produccion, ProduccionRepository produccionRepository, Initializable controllerAnterior, String vista, User user) {
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccion = produccion;
        this.produccionRepository = produccionRepository;
        this.esFavoritaRepository = new EsFavoritaRepository(new SQLEsFavoritaDAO(), produccionRepository, new UserRepository(new SQLUserDAO()));
        this.user = user;
        this.controllerAnterior = controllerAnterior;
        this.vista = vista;
        valorar = 0;
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 1 estrella, se encenderá 1 solamente 1 estrella (cambiará la imagen).
     * @author Andreu Francés
     * @param event
     */
    @FXML
    private void encender1(MouseEvent event) throws URISyntaxException{
        valorar = 1;
        encender(valorar);
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 2 estrellas, se encenderan la misma y la anterior, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */
    @FXML
    private void encender2(MouseEvent event) throws URISyntaxException{
        valorar = 2;
        encender(valorar);
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 3 estrellas, se encenderan la misma y las anteriores, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */
    @FXML
    private void encender3(MouseEvent event) throws URISyntaxException {
        valorar = 3;
        encender(valorar);
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 4 estrellas, se encenderan la misma y las anteriores, es decir, cambiarán las imagenes.
     * @author Andreu Francés
     * @param event
     */
    @FXML
    private void encender4(MouseEvent event) throws URISyntaxException{
        valorar = 4;
        encender(valorar);
    }

    /**
     * Para que cuando vayamos a valorar una producción si la valoramos con 5 estrellas, se encenderan la misma y las anteriores
     * es decir, todas las posibles (cambiando la imagen de todas las estrellas).
     * @author Andreu Francés
     * @param event
     */
    @FXML
    private void encender5(MouseEvent event) throws URISyntaxException {
        valorar = 5;
        encender(valorar);
    }

    /**
     * Esta clase se encarga de dependiendo de la nota que recibe, enciende ese número de estrellas
     * @author Andreu Francés
     */
    private void encender(int nota) throws URISyntaxException {
        if (nota==1){
            estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella2.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        }else if (nota==2){
            estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella3.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        }else if (nota==3){
            estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella4.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
            estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        }else if (nota==4){
            estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella5.setImage(new Image(getPathImage("/images/EstrellaBlanca.png")));
        }else if (nota==5){
            estrella1.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella2.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella3.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella4.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
            estrella5.setImage(new Image(getPathImage("/images/EstrellaSeleccionada.png")));
        }
    }

    /**
     * Se encarga de recoger el comentario introducido a la hora de valorar una pelicula, y de pasarle
     * a la base de datos tanto el comentario como la valoracion
     * @author Pablo Marin
     */
    @FXML
    private void comentar(MouseEvent event) throws DatabaseErrorException, NotFoundException {
        Valoracion valoracion = new Valoracion(produccion.getId(),user.getId(),valorar,opinionComentario.getText());
        if (valoracionRepository.getById(produccion.getId(), user.getId())){
            valoracionRepository.save(valoracion);
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setTitle( "Comentario actualizado con éxito" );
            alert.setHeaderText( "" );
            alert.setContentText( "Tu comentario ha sido actualizado con éxito, gracias por tu colaboración");
            alert.showAndWait();
        }else {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            valoracionRepository.save(valoracion);
            alert.setTitle( "Comentario guardado con éxito" );
            alert.setHeaderText( "" );
            alert.setContentText( "Tu comentario ha sido guardado con éxito, gracias por tu colaboración");
            alert.showAndWait();
        }


    }

    /**
     * Para que cuando estemos en esta vista y le demos atrás, se encarga de llevarnos al controlador Main
     * @author Pablo Marin
     * @param event
     */
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
        try {
            if (valoracionRepository.getById(produccion.getId(), user.getId())){
                Valoracion valoracion ;
                for (Valoracion valoracio:valoracionRepository.findAll()) {
                    if (valoracio.getId_produccion().equals(produccion.getId())&&valoracio.getId_usuario()==(user.getId())){
                        valoracion=valoracio;
                        encender(valoracion.getNota());
                        valorar=valoracion.getNota();
                        opinionComentario.setText(valoracion.getComentario());
                    }
                }

                
            }
        } catch (NotFoundException | DatabaseErrorException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        descripcion.setText(produccion.getGuion());
        descripcion.setWrapText(true);
        descripcion.setPrefWidth(370);


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
