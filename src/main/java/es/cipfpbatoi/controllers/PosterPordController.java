package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.respositories.*;
import es.cipfpbatoi.models.dto.prods.Visualizar;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import es.cipfpbatoi.models.respositories.VisualizarRepository;
import es.cipfpbatoi.utils.URLChecker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URISyntaxException;

import static es.cipfpbatoi.utils.URLChecker.checkURL;

public class PosterPordController extends ListCell<Produccion> {
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView posterImageView;
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private VisualizarRepository visualizarRepository;
    private Produccion produccion;
    private Initializable controllerAnterior;
    private GeneroRepository generoRepository;
    private String vista;
    private User user;

    public PosterPordController(ValoracionRepository valoracionRepository, RankingRepository rankingRepository, ProduccionRepository produccionRepository, Initializable controllerAnterior, String vista, User user, VisualizarRepository visualizarRepository) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/posterProd.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            ex. printStackTrace() ;
        }
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.produccionRepository = produccionRepository;
        this.controllerAnterior= controllerAnterior;
        this.vista= vista;
        this.visualizarRepository = visualizarRepository;
        this.user = user;
    }
    /**
     * Actualiza los datos de la producción y muestra la portada
     * @author Marcos Sanz
     * @param produccion la produccion
     * @param empty si esta vacio
     */
    @Override
    protected void updateItem(Produccion produccion, boolean empty) {

        super.updateItem(produccion, empty);
        this.produccion= produccion;

        if (empty) {
            setGraphic(null);
        } else {
            setPosterImage(produccion);
            setGraphic(root);
        }
    }
    /**
     * Al hacer clic en la portada, se suma una visualizacion a la produccion y el usuario.
     * Luego cambia a la vista ControllerDetalles con la produccion
     * @author Marcos Sanz
     * @param event clic
     */
    @FXML
    private void changeToProduccion(MouseEvent event){
        try {
            this.visualizarRepository.save(new Visualizar(produccion.getId(), user.getId()));
            this.visualizarRepository.sumarVisualizacion(produccion.getId());
            ControllerDetalles controllerDetalles= new ControllerDetalles(valoracionRepository, rankingRepository, produccion, produccionRepository, this.controllerAnterior, this.vista, user);
            ChangeScene.change(event, controllerDetalles, "/views/detail_production.fxml");
        } catch ( IOException ex) {
            ex.printStackTrace();
        } catch (DatabaseErrorException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Comprueba si el link a la portada es vaído, si es válido le inserta la imágen a la portada si no pone una imágen default
     * @author Marcos Sanz
     * @author Martin Peidro
     * @param produccion produccion
     */
    @FXML
    private void setPosterImage(Produccion produccion) {
        posterImageView.maxHeight(225);
        posterImageView.maxWidth(150);
            try {
                if ( URLChecker.checkURL( produccion.getPoster() ) ) {
                    posterImageView.setImage(new Image(produccion.getPoster()));
                } else {
                    posterImageView.setImage( new Image( getPathImage( "/images/default.png" ) ) );
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException( e );
            }
    }
    /**
     * Obtiene el url de la imágen para poder mostrarla
     * @author Marcos Sanz
     * @param fileName fichero
     * @return el fichero válido de la imágen
     */
    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
