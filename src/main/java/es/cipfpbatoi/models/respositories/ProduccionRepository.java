package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public class ProduccionRepository {
    private ProduccionDAO produccionDAO;

    public ProduccionRepository(ProduccionDAO produccionDAO) {
        this.produccionDAO = produccionDAO;
    }

    public ArrayList<Produccion> findAll() throws DatabaseErrorException{
        return this.produccionDAO.findAll();
    }
    public void save(Produccion produccion) throws DatabaseErrorException{
        this.produccionDAO.save(produccion);
    }
    public Produccion getById (String id) throws NotFoundException, DatabaseErrorException{
        return this.produccionDAO.getById(id);
    }
}
