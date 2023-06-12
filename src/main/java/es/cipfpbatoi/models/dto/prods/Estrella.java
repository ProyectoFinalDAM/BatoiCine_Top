package es.cipfpbatoi.models.dto.prods;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static es.cipfpbatoi.App.ESTRELLA_APAGADA_COLOR;
import static es.cipfpbatoi.App.ESTRELLA_ENCENDIDA_COLOR;

public class Estrella extends HBox {

    private int valor;
    private Polygon estrella;

    public Estrella(int valor) {
        this.valor = valor;

        estrella = new Polygon(
                10, 0,
                15, 7,
                23, 8,
                17, 14,
                18, 22,
                10, 18,
                2, 22,
                3, 14,
                -3, 8,
                5, 7
        );
        estrella.setFill(ESTRELLA_APAGADA_COLOR);
        Label label = new Label(String.valueOf(valor + 1));
        label.setPadding(new Insets(0, 0, 0, 5));

        this.getChildren().addAll(estrella, label);

        this.setOnMouseEntered(this::encenderEstrellas);
        this.setOnMouseExited(this::apagarEstrellas);
    }

    /**
     * Enciende la estrella de la valoración
     * @author Pablo Marin
     * @param event
     */

    private void encenderEstrellas(MouseEvent event) {
        for (int i = 0; i <= valor; i++) {
            ((Polygon) this.getChildren().get(0)).setFill(ESTRELLA_ENCENDIDA_COLOR);
        }
    }

    public void encenderEstrellas() {
        for (int i = 0; i <= valor; i++) {
            ((Polygon) this.getChildren().get(0)).setFill(ESTRELLA_ENCENDIDA_COLOR);
        }
    }

    /**
     * @author Andreu Francés
     * @return retorna si true si esta encendida, en caso contrario false
     */

    public boolean esEncendida(){
        if (this.estrella.getFill().equals(ESTRELLA_ENCENDIDA_COLOR)){
            return true;
        }
        return false;
    }

    /**
     * Apaga la estrella de la valoración
     * @author Pablo Marin
     * @param event
     */

    private void apagarEstrellas(MouseEvent event) {
        for (int i = 0; i <= valor; i++) {
            ((Polygon) this.getChildren().get(0)).setFill(ESTRELLA_APAGADA_COLOR);
        }
    }
}

