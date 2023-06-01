package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.respositories.GeneroRepository;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
import es.cipfpbatoi.models.respositories.RankingRepository;
import es.cipfpbatoi.models.respositories.ValoracionRepository;
import es.cipfpbatoi.utils.AlertCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private GeneroRepository generoRepository;

    private String titulo;
    private Genero genero;

    private Tipo tipo;
    @FXML private Text peliculasText;
    @FXML private Text seriesText;
    @FXML private TextField textFieldSearch;
    @FXML private ComboBox<Genero> generoComboBox;
    @FXML private ImageView back;
    @FXML private ListView<Produccion> portadaListView;
    @FXML private Label productionType;

    public SearchController(ProduccionRepository produccionRepository, String titulo, Genero genero, GeneroRepository generoRepository) {
        this.produccionRepository = produccionRepository;
        this.generoRepository= generoRepository;
        this.titulo = titulo;
        this.genero = genero;
    }

    public SearchController(ProduccionRepository produccionRepository, Tipo tipo) {
        this.produccionRepository = produccionRepository;
        this.tipo                 = tipo;
    }

    public SearchController(ProduccionRepository produccionRepository, RankingRepository rankingRepository, ValoracionRepository valoracionRepository) {
        this.produccionRepository = produccionRepository;
        this.rankingRepository    = rankingRepository;
        this.valoracionRepository = valoracionRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.textFieldSearch.setText( this.titulo );
        this.generoComboBox.setValue( this.genero );

        if(textFieldSearch.getText() != null  && generoComboBox.getValue() != null){
            this.portadaListView.setItems( getData() );
        } else if ( this.tipo.equals( Tipo.MOVIE ) ) {
            this.portadaListView.setItems( getAllFilms() );
        } else {
            this.portadaListView.setItems( getAllSeries() );
        }


        this.portadaListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController(valoracionRepository, rankingRepository, produccionRepository, this, "/views/search.fxml"));
    }

    private ArrayList<Produccion> getCoincidencias(){
        ArrayList<Produccion> coincidencias = new ArrayList<>();

        if ( textFieldSearch.getText() == null ){
           coincidencias.addAll( this.produccionRepository.getCoincidenciaGenero( generoComboBox.getValue()));
        } else if ( textFieldSearch.getText() == null  && generoComboBox.getValue() == null) {
            try {
                coincidencias.addAll( this.produccionRepository.findAll() );
            } catch (DatabaseErrorException e) {
                throw new RuntimeException( e );
            }
        } else {
            coincidencias.add( this.produccionRepository.getCoincidenciaTitulo( textFieldSearch.getText() ) );
        }

        return coincidencias;
    }

    private ObservableList<Produccion> getData(){
        return FXCollections.observableArrayList(getCoincidencias());
    }

    @FXML
    private void goBack(MouseEvent event){
        try {
            MainController mainController = new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository);
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
}