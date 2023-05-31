package es.cipfpbatoi.controllers;

import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDetalles implements Initializable {
    @FXML
    private Button uno;
    @FXML
    private Button dos;
    @FXML
    private Button tres;
    @FXML
    private Button cuatro;
    @FXML
    private Button cinco;
    @FXML
    private Button verifiar;
    @FXML
    private TextField descripcion;

    private ValoracionDAO valoracionDAO;
    private RankingDAO rankingDAO;
    private Produccion produccion;

    public ControllerDetalles(ValoracionDAO valoracionDAO, RankingDAO rankingDAO, Produccion produccion) {
        this.valoracionDAO = valoracionDAO;
        this.rankingDAO = rankingDAO;
        this.produccion = produccion;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
