package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;

import java.util.ArrayList;

public class ValoracionRepository {
    ValoracionDAO valoracionDAO;

    public ValoracionRepository(ValoracionDAO valoracionDAO) {
        this.valoracionDAO = valoracionDAO;
    }

    public ArrayList<Valoracion> findAll() throws DatabaseErrorException {
        return valoracionDAO.findAll();
    }

    public void save(Valoracion valoracion) throws DatabaseErrorException {
        valoracionDAO.save(valoracion);
    }
}
