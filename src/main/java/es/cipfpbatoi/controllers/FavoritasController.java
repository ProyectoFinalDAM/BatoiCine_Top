package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.respositories.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class FavoritasController implements Initializable {

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
    @FXML
    private ImageView back;
    private GeneroRepository generoRepository;
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private VisualizarRepository visualizarRepository;
    private EsFavoritaRepository esFavoritaRepository;
    private User user;

    public FavoritasController(ProduccionRepository produccionRepository, ValoracionRepository valoracionRepository, RankingRepository rankingRepository, GeneroRepository generoRepository, User user, VisualizarRepository visualizarRepository, EsFavoritaRepository esFavoritaRepository) {
        this.produccionRepository = produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.generoRepository= generoRepository;
        this.user = user;
        this.visualizarRepository= visualizarRepository;
        this.esFavoritaRepository= esFavoritaRepository;
    }

    /**
     * Inicializamos todas las producciones que son favoritas por el usuario, cargando su portada y dividiendolas en peliculas y series
     * insertar la descripción de la producción junto a las plataformas en las que está disponible y sus actores.
     * @author Marcos Sanz
     * @param url url
     * @param resourceBundle resource
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seriesListView.setOrientation(Orientation.HORIZONTAL);
        this.seriesListView.setItems(getSeries());
        peliculasListView.setOrientation(Orientation.HORIZONTAL);
        this.peliculasListView.setItems(getPeliculas());
        this.peliculasListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository, this, "/views/main.fxml", user, visualizarRepository));
        this.seriesListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository,this, "/views/main.fxml", user, visualizarRepository));
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
            lupaImageView.setImage(new Image(getPathImage("/images/lupaIcon.png")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.generoComboBox.setItems(getGeneros());
        this.generoComboBox.setDisable(true);
        this.searchTextField.setEditable(false);
    }

    /**
     * Recogemos todos los géneros
     * @author Marcos Sanz
     * @return ObservableList con todos los géneros
     */

    private ObservableList<Genero> getGeneros() {
        return FXCollections.observableArrayList(generoRepository.findAll());
    }

    /**
     * Recogemos todas las películas que ya han sido marcadas como favoritas por el usuario.
     * @author Marcos Sanz
     * @return ObservableList con todos las peliculas favoritas.
     */

    private ObservableList<Produccion> getPeliculas() {
        try {
            ArrayList<Produccion> peliculasFav= new ArrayList<>();
            for (Produccion prod: esFavoritaRepository.getUserFavs(user)) {
                if (prod.getTipo().equals(Tipo.MOVIE)){
                    peliculasFav.add(prod);
                }
            }

            return FXCollections.observableArrayList(peliculasFav);
        } catch (DatabaseErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recogemos todas las series que ya han sido marcadas como favoritas por el usuario.
     * @author Marcos Sanz
     * @return ObservableList con todos las series favoritas.
     */

    private ObservableList<Produccion> getSeries(){
        try {
            ArrayList<Produccion> peliculasFav= new ArrayList<>();
            for (Produccion prod: esFavoritaRepository.getUserFavs(user)) {
                if (prod.getTipo().equals(Tipo.TVSHOW)){
                    peliculasFav.add(prod);
                }
            }

            return FXCollections.observableArrayList(peliculasFav);
        } catch (DatabaseErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * En este método nos encargamos de que cuando cliquemos en el título de "películas" nos lleve al catálogo de todas las peliculas disponibles.
     * @author Marcos Sanz
     * @param event clic
     */

    @FXML
    private void changeToPeliculas(MouseEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SearchController searchController= new SearchController(produccionRepository, rankingRepository,valoracionRepository,Tipo.MOVIE, generoRepository, visualizarRepository, user, esFavoritaRepository);
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * En este método nos encargamos de que cuando cliquemos en el título de "series" nos carge el catálogo de todas las series disponibles
     * @author Marcos Sanz
     * @param event clic
     */

    @FXML
    private void changeToSeries(MouseEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SearchController searchController= new SearchController(produccionRepository, rankingRepository,valoracionRepository,Tipo.TVSHOW, generoRepository, visualizarRepository, user, esFavoritaRepository);
            ChangeScene.change(stage, searchController, "/views/search.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Volvemos atrás a la vista main del programa
     * @author Marcos Sanz
     * @param event clic
     */

    @FXML
    private void goBack(MouseEvent event){
        try {
            MainController mainController = new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository,user, visualizarRepository, esFavoritaRepository);
            ChangeScene.change(event, mainController, "/views/main.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Para poder cargar las imagenes locales mediante su url
     * @author Marcos Sanz
     * @param fileName nombre fichero
     * @return String , la ruta de dicha imagen.
     */

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}