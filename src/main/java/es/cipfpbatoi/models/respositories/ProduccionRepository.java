package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class ProduccionRepository {
    private ProduccionDAO produccionDAO;

    public ProduccionRepository(ProduccionDAO produccionDAO) {
        this.produccionDAO = produccionDAO;
    }

    public ArrayList<Produccion> findAll() throws DatabaseErrorException{
        return this.produccionDAO.findAll();
    }

    public ArrayList<Produccion> findAll(String tipo) throws DatabaseErrorException{
        return this.produccionDAO.findAll(tipo);
    }

    public void save(Produccion produccion) throws DatabaseErrorException{
        this.produccionDAO.save(produccion);
    }
    public Produccion getById (String id) throws NotFoundException, DatabaseErrorException{
        return this.produccionDAO.getById(id);
    }
    public ArrayList<Produccion> getRecommendedFilms() throws DatabaseErrorException{
        return this.produccionDAO.getRecommendedFilms();
    }
    public ArrayList<Produccion> getRecommendedSeries() throws DatabaseErrorException{
        return this.produccionDAO.getRecommendedSeries();
    }

    public Produccion getCoincidenciaTitulo(String titulo){
        return this.produccionDAO.getCoincidenciaTitulo( titulo );
    }

    public ArrayList<Produccion> getCoincidenciaGenero(Genero genero){
        return this.produccionDAO.getCoincidenciaGenero( genero );
    }

    public ArrayList<Produccion> getCoincidenciaGeneroTitulo(String titulo, Genero genero){
        return this.produccionDAO.getCoincidenciaGeneroTitulo( titulo, genero );
    }
}
