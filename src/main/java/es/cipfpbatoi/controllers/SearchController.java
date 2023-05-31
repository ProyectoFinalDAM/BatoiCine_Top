package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.respositories.ProduccionRepository;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    private ProduccionRepository produccionRepository;

    private String titulo;
    private Genero genero;
    @FXML private Text peliculasText;
    @FXML private Text seriesText;
    @FXML private TextField textFieldSearch;
    @FXML private ComboBox<Genero> generoComboBox;
    @FXML private ImageView back;
    @FXML private ListView<Produccion> portadaListView;
    @FXML private Label productionType;

    public SearchController(ProduccionRepository produccionRepository, String titulo, Genero genero) {
        this.produccionRepository = produccionRepository;
        this.titulo = titulo;
        this.genero = genero;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.textFieldSearch.setText( this.titulo );
        this.generoComboBox.setValue( this.genero );
        this.portadaListView.setItems( getData() );
        this.portadaListView.setCellFactory((ListView<Produccion> p) -> new PosterPordController());
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
            MainController mainController = new MainController(produccionRepository);
            ChangeScene.change( (Stage) event, mainController, "/resource/views/main.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public ArrayList<Produccion> getAllFilms(javafx.scene.input.MouseEvent event){
         try {
             return this.produccionRepository.findAll( Tipo.MOVIE.toString() );
        } catch (DatabaseErrorException e) {
            throw new RuntimeException( e );
        }
    }
    @FXML
    public ArrayList<Produccion> getAllSeries(javafx.scene.input.MouseEvent event){
        try {
            return this.produccionRepository.findAll( Tipo.TVSHOW.toString() );
        } catch (DatabaseErrorException e) {
            throw new RuntimeException( e );
        }
    }
}
