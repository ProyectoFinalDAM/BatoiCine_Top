package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.respositories.*;
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
    private User user;
    @FXML
    private Button buttonBuscar;

    public MainController(ProduccionRepository produccionRepository, ValoracionRepository valoracionRepository, RankingRepository rankingRepository, GeneroRepository generoRepository) {
        this.produccionRepository = produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.generoRepository= generoRepository;
    }
    public MainController(ProduccionRepository produccionRepository, ValoracionRepository valoracionRepository, RankingRepository rankingRepository, GeneroRepository generoRepository, User user) {
        this.produccionRepository = produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.generoRepository= generoRepository;
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seriesListView.setOrientation(Orientation.HORIZONTAL);
        this.seriesListView.setItems(getSeries());
        peliculasListView.setOrientation(Orientation.HORIZONTAL);
        this.peliculasListView.setItems(getPeliculas());
        this.peliculasListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository, this, "/views/main.fxml", user));
        this.seriesListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository,this, "/views/main.fxml", user));
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
            lupaImageView.setImage(new Image(getPathImage("/images/lupaIcon.png")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.generoComboBox.setItems(getGeneros());
    }
    private ObservableList<Genero> getGeneros(){
        return FXCollections.observableArrayList(generoRepository.findAll());
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
            SearchController searchController= new SearchController(produccionRepository, this.searchTextField.getText(), this.generoComboBox.getValue(), generoRepository, rankingRepository, valoracionRepository);
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void changeToPeliculas(MouseEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SearchController searchController= new SearchController(produccionRepository, rankingRepository,valoracionRepository,Tipo.MOVIE);
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void changeToSeries(MouseEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SearchController searchController= new SearchController(produccionRepository, rankingRepository,valoracionRepository,Tipo.MOVIE);
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
