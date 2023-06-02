package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import es.cipfpbatoi.models.respositories.VisualizarRepository;
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
    private User user;
    private String vista;
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
        this.visualizarRepository= visualizarRepository;
        this.user= user;
    }
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
    @FXML
    private void changeToProduccion(MouseEvent event){
        try {
            visualizarRepository.save(new Visualizar(produccion.getId(), user.getId()));
        } catch (DatabaseErrorException e) {
            throw new RuntimeException(e);
        }
        try {
            ControllerDetalles controllerDetalles= new ControllerDetalles(valoracionRepository, rankingRepository, produccion, produccionRepository, this.controllerAnterior, this.vista);
            ChangeScene.change(event, controllerDetalles, "/views/detail_production.fxml");
        } catch ( IOException ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    private void setPosterImage(Produccion produccion) {
        posterImageView.maxHeight(225);
        posterImageView.maxWidth(150);
        posterImageView.setImage(new Image(produccion.getPoster()));
    }
}