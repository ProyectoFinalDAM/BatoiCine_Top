package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.respositories.GeneroRepository;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import es.cipfpbatoi.utils.AlertCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView lupaImageView;
    @FXML
    private Text peliculasText;
    @FXML
    private Text seriesText;
    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<Produccion> peliculasListView;
    @FXML
    private ListView<Produccion> seriesListView;
    @FXML
    private ComboBox<Genero> generoComboBox;
    private GeneroRepository generoRepository;
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    @FXML
    private Button buttonBuscar;


    public MainController(ProduccionRepository produccionRepository, ValoracionRepository valoracionRepository, RankingRepository rankingRepository) {
        this.produccionRepository = produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seriesListView.setOrientation(Orientation.HORIZONTAL);
        this.seriesListView.setItems(getSeries());
        peliculasListView.setOrientation(Orientation.HORIZONTAL);
        this.peliculasListView.setItems(getPeliculas());
        this.peliculasListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository, this, "/views/main.fxml"));
        this.seriesListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository,this, "/views/main.fxml"));
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
            lupaImageView.setImage(new Image(getPathImage("/images/lupaIcon.png")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private ObservableList<Produccion> getPeliculas(){
        try {
            return FXCollections.observableArrayList(produccionRepository.getRecommendedFilms());
        } catch (DatabaseErrorException e) {
            throw new RuntimeException(e);
        }
    }
    private ObservableList<Produccion> getSeries(){
        try {
            return FXCollections.observableArrayList(produccionRepository.getRecommendedSeries());
        } catch (DatabaseErrorException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void buscarProduccion(ActionEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SearchController searchController= new SearchController(produccionRepository, this.searchTextField.getText(), this.generoComboBox.getValue());
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void changeToPeliculas(MouseEvent event){
        AlertCreator.infoAlert("Cambiar a peliculas");
    }
    @FXML
    private void changeToSeries(MouseEvent event){
        AlertCreator.infoAlert("Cambiar a series");
    }
    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
