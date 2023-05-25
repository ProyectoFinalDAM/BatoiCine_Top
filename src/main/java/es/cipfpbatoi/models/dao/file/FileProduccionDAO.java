package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public class FileProduccionDAO implements ProduccionDAO {


    @Override
    public ArrayList<Produccion> findAll() throws DatabaseErrorException {
        return null;
    }

    @Override
    public ArrayList<Produccion> findAll() {
        return null;
    }

    @Override
    public void save(Produccion produccion) {

    }

    @Override
    public Produccion getById(String dni) throws NotFoundException, DatabaseErrorException {
        return null;
    }
}
