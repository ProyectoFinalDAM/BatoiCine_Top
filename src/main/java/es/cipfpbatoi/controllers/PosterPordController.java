package es.cipfpbatoi.controllers;

import es.cipfpbatoi.models.dto.prods.Produccion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URISyntaxException;

public class PosterPordController extends ListCell<Produccion> {
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView posterImageView;
    public PosterPordController() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/posterProd.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            ex. printStackTrace() ;
        }
    }
    @Override
    protected void updateItem(Produccion produccion, boolean empty) {

        super.updateItem(produccion, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setPosterImage(produccion);
            setGraphic(root);
        }
    }
    private void setPosterImage(Produccion produccion) {
        posterImageView.maxHeight(225);
        posterImageView.maxWidth(150);
        posterImageView.setImage(new Image(produccion.getPoster()));
    }
}
