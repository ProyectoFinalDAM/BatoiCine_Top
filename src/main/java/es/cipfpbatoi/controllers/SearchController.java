package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.WrongParameterException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.respositories.*;
import es.cipfpbatoi.utils.AlertCreator;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    private final static int INDICE_PAGINA_INICIAL = 0;
    private final static int FILAS_POR_PAGINA = 5;
    private int currentPageIndex = 0;
    private int totalDataToShow;
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private GeneroRepository generoRepository;
    private VisualizarRepository visualizarRepository;
    private User user;

    private String titulo;
    private Genero genero;

    private Tipo tipo;
    @FXML private Text peliculasText;
    @FXML private Text seriesText;
    @FXML private TextField textFieldSearch;
    @FXML private ComboBox<Genero> generoComboBox;
    @FXML private ImageView back;
    @FXML private ListView<Produccion> portadaListView;
    @FXML private Label     productionType;
    @FXML private       Hyperlink hlAtras;
    @FXML private Hyperlink hlSiguiente;
    private EsFavoritaRepository esFavoritaRepository;

    private ArrayList<Produccion> produccions;

    public SearchController(ProduccionRepository produccionRepository, String titulo, Genero genero, GeneroRepository generoRepository, RankingRepository rankingRepository, ValoracionRepository valoracionRepository, VisualizarRepository visualizarRepository, User user, EsFavoritaRepository esFavoritaRepository) {
        this.produccionRepository = produccionRepository;
        this.generoRepository= generoRepository;
        this.titulo = titulo;
        this.genero = genero;
        this.rankingRepository    = rankingRepository;
        this.valoracionRepository = valoracionRepository;
        this.visualizarRepository= visualizarRepository;
        this.produccions= new ArrayList<>();
        this.user= user;
        this.esFavoritaRepository=esFavoritaRepository;
    }

    public SearchController(ProduccionRepository produccionRepository, RankingRepository rankingRepository, ValoracionRepository valoracionRepository, Tipo tipo, GeneroRepository generoRepository, VisualizarRepository visualizarRepository, User user, EsFavoritaRepository esFavoritaRepository) {
        this.generoRepository= generoRepository;
        this.produccionRepository = produccionRepository;
        this.rankingRepository    = rankingRepository;
        this.valoracionRepository = valoracionRepository;
        this.tipo                 = tipo;
        this.visualizarRepository= visualizarRepository;
        this.produccions= new ArrayList<>();
        this.user= user;
        this.esFavoritaRepository=esFavoritaRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.textFieldSearch.setText( this.titulo );
        this.generoComboBox.setValue( this.genero );

        if( textFieldSearch.getText()==null || textFieldSearch.getText().equals("") && generoComboBox.getValue() == null){
            if ( this.tipo != null && this.tipo.equals( Tipo.MOVIE ) ) {
                this.portadaListView.setItems( getAllFilms() );
                this.produccions.addAll( getAllFilms() );
            } else if (this.tipo != null && this.tipo.equals( Tipo.TVSHOW )){
                this.portadaListView.setItems( getAllSeries() );
                this.produccions.addAll( getAllSeries() );
            }else{
                this.portadaListView.setItems( getData() );
                this.produccions.addAll( getData() );
            }
        }else if(textFieldSearch.getText().equals("")  && generoComboBox.getValue() != null){
            this.portadaListView.setItems( getData() );
            this.produccions.addAll( getData() );
        }else if(textFieldSearch.getText() != null  && generoComboBox.getValue() == null){
            this.portadaListView.setItems( getData() );
            this.produccions.addAll( getData() );
        }else if(textFieldSearch.getText() != null  && generoComboBox.getValue() != null){
            this.portadaListView.setItems( getData() );
            this.produccions.addAll( getData() );
        }

        this.generoComboBox.setDisable(true);
        this.textFieldSearch.setEditable(false);
        this.portadaListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository, this, "/views/search.fxml", user, visualizarRepository));
        this.totalDataToShow= this.produccions.size();
        try {
            showPage(portadaListView, hlAtras, hlSiguiente);
        } catch (WrongParameterException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Produccion> getCoincidencias(){
        ArrayList<Produccion> coincidencias = new ArrayList<>();

        try {
            if (textFieldSearch.getText()==null || textFieldSearch.getText().equals("") && generoComboBox.getValue() == null) {
                coincidencias.addAll( this.produccionRepository.findAll());
            } else if ( textFieldSearch.getText().equals("") && generoComboBox.getValue() != null){
                coincidencias.addAll( this.produccionRepository.getCoincidenciaGenero( generoComboBox.getValue()));
            } else if(textFieldSearch.getText() != null  && generoComboBox.getValue() == null){
                coincidencias.add( this.produccionRepository.getCoincidenciaTitulo( textFieldSearch.getText() ) );
            } else if(textFieldSearch.getText() != null  && generoComboBox.getValue() != null){
                coincidencias.addAll( this.produccionRepository.getCoincidenciaGeneroTitulo( textFieldSearch.getText(),generoComboBox.getValue() ) );
            } else if ( generoComboBox.getValue() == null ){
                coincidencias.add( this.produccionRepository.getCoincidenciaTitulo( textFieldSearch.getText() ) );
            } else {
                coincidencias.addAll(this.produccionRepository.findAll());
            }
        } catch (DatabaseErrorException e) {
            throw new RuntimeException(e);
        }


        return coincidencias;
    }

    private ObservableList<Produccion> getData(){
        return FXCollections.observableArrayList(getCoincidencias());
    }

    @FXML
    private void goBack(MouseEvent event){
        try {
            MainController mainController = new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository,user, visualizarRepository, esFavoritaRepository);
            ChangeScene.change(event, mainController, "/views/main.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Produccion> getAllFilms(){
        try {
            return FXCollections.observableArrayList(this.produccionRepository.findAll( Tipo.MOVIE.toString() ));
        } catch (DatabaseErrorException e) {
            throw new RuntimeException( e );
        }
    }

    private ObservableList<Produccion> getAllSeries(){
        try {
            return FXCollections.observableArrayList(this.produccionRepository.findAll( Tipo.TVSHOW.toString() ));
        } catch (DatabaseErrorException e) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Es una acción de botón que viaja a la página siguiente de la paginación
     * @author Martin Peidro
     */

    @FXML
    private void handleLinkSiguiente() {
        try {
            nextPage(portadaListView, hlAtras, hlSiguiente);
        } catch (WrongParameterException ex) {
            AlertCreator.infoAlert(ex.getMessage());
        }
    }

    /**
     * Va a la página siguiente
     * @author Martin Peidro
     * @param listView
     * @param atras
     * @param siguiente
     * @throws WrongParameterException
     */

    private void nextPage(ListView<Produccion> listView, Hyperlink atras, Hyperlink siguiente) throws WrongParameterException{
        currentPageIndex++;
        showPageWithTransition(listView, atras, siguiente);
    }

    /**
     * Muestra la paginación con transición
     * @author Martin Peidro
     * @param listView
     * @param atras
     * @param siguiente
     */

    private void showPageWithTransition(ListView<Produccion> listView, Hyperlink atras, Hyperlink siguiente) {
        FadeTransition fadeOut = createFadeTransition(1.0, 0.0);
        fadeOut.setOnFinished(event -> {
            try {
                showPage(listView, atras, siguiente);
                FadeTransition fadeIn = createFadeTransition(0.0, 1.0);
                fadeIn.play();
            }catch (WrongParameterException ex) {
                ex.getMessage();
            }
        });
        fadeOut.play();
    }

    /**
     * Vuelve a la página previa
     * @author Martin Peidro
     * @param listView
     * @param atras
     * @param siguiente
     * @throws WrongParameterException
     */

    private void previousPage(ListView<Produccion> listView, Hyperlink atras, Hyperlink siguiente) throws WrongParameterException {
        if (currentPageIndex > 0) {
            currentPageIndex--;
            showPageWithTransition(listView, atras, siguiente);
        }
    }

    /**
     * Es una acción de botón que vuleve a la página anterior de la paginación
     * @author Martin Peidro
     */

    @FXML
    private void handleLinkAtras() {
        try {
            previousPage(portadaListView, hlAtras, hlSiguiente);
        } catch (WrongParameterException ex) {
            AlertCreator.infoAlert(ex.getMessage());
        }
    }

    /**
     * Muestra la paginación
     * @author Martin Peidro
     * @param listView
     * @param atras
     * @param siguiente
     * @throws WrongParameterException
     */

    private void showPage(ListView<Produccion> listView, Hyperlink atras, Hyperlink siguiente) throws WrongParameterException {
        listView.getItems().clear();
        List<Produccion> pageData = fetchDataForPage();
        listView.getItems().addAll(pageData);
        updateLinksState(atras, siguiente);
    }

    /**
     * Recoge las producciones que se va a mostrar según su posición
     * @author Martin Peidro
     * @return una lista con las producciones a mostrar
     * @throws WrongParameterException
     */

    private List<Produccion> fetchDataForPage() throws WrongParameterException {
        int startIndex = currentPageIndex * FILAS_POR_PAGINA;
        int endIndex = Math.min(startIndex + FILAS_POR_PAGINA, totalDataToShow);
        return findAll(startIndex, endIndex);
    }

    /**
     * Actualiza la página de la paginación
     * @author Martin Peidro
     * @param previousPageButton
     * @param nextPageButton
     */

    private void updateLinksState(Hyperlink previousPageButton, Hyperlink nextPageButton) {
        previousPageButton.setDisable(currentPageIndex <= 0);
        int totalPageCount = (int) Math.ceil((double) totalDataToShow / FILAS_POR_PAGINA);
        nextPageButton.setDisable(currentPageIndex >= totalPageCount - 1);
    }

    /**
     * Crea una transición
     * @author Martin Peidro
     * @param fromValue
     * @param toValue
     * @return devuleve una transición
     */

    private FadeTransition createFadeTransition(double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(new Duration(500), portadaListView);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    /**
     * Recoge los elementos que van a ser mostrados en la página
     * @@author Martin Peidro
     * @param fromIndex
     * @param toIndex
     * @return una lista de los productos encontrados
     * @throws WrongParameterException
     */

    private List<Produccion> findAll(int fromIndex, int toIndex) throws WrongParameterException {
        if (fromIndex < 0 || fromIndex > this.produccions.size()-1)
            throw new WrongParameterException("El índice del primer elemento a mostrar es incorrecto: " + fromIndex);
        if (toIndex < fromIndex)
            throw new WrongParameterException("El índice del último elemento a mostrar es superior al primero: " + toIndex);

        if (toIndex > this.produccions.size()-1) {
            toIndex = this.produccions.size();
        }

        return this.produccions.subList(fromIndex, toIndex);
    }

    /**
     * Muestra todas las peliculas con paginación
     * @author Martin Peidro
     * @author Marcos Sanz
     * @param event
     */

    @FXML
    private void showFilms(MouseEvent event){
        try {
            this.produccions.clear();
            this.portadaListView.setItems( getAllFilms() );
            this.produccions.addAll( getAllFilms() );
            this.totalDataToShow= this.produccions.size();
            this.currentPageIndex=0;
            showPage(portadaListView, hlAtras, hlSiguiente);
        } catch (WrongParameterException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Muestra todas las series con paginación
     * @author Martin Peidro
     * @author Marcos Sanz
     * @param event
     */

    @FXML
    private void showShows(MouseEvent event){
        try {
            this.produccions.clear();
            this.portadaListView.setItems( getAllSeries() );
            this.produccions.addAll( getAllSeries() );
            this.totalDataToShow= this.produccions.size();
            this.currentPageIndex=0;
            showPage(portadaListView, hlAtras, hlSiguiente);
        } catch (WrongParameterException e) {
            throw new RuntimeException(e);
        }
    }
}