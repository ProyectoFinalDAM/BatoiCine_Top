package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ProduccionDAO {

    ArrayList<Produccion> findAll() throws DatabaseErrorException;
    ArrayList<Produccion> getRecommendedFilms() throws DatabaseErrorException;
    ArrayList<Produccion> getRecommendedSeries() throws DatabaseErrorException;
    ArrayList<Produccion> findAll(String tipo) throws DatabaseErrorException;
    void save(Produccion produccion) throws DatabaseErrorException;
    Produccion getById (String id) throws NotFoundException, DatabaseErrorException;
    String getPortadaProduccion(Produccion produccion) throws DatabaseErrorException;
    Produccion getCoincidenciaTitulo(String text);

    ArrayList<Produccion> getCoincidenciaGenero(Genero genero);
    ArrayList<Produccion> getCoincidenciaGeneroTitulo(String titulo, Genero genero);
}
