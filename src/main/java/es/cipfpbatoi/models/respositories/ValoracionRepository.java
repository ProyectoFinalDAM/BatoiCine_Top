package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dao.sql.SQLValoracionDAO;
import es.cipfpbatoi.models.dto.prods.Valoracion;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
 */

public class ValoracionRepository {
    ValoracionDAO valoracionDAO;

    public ValoracionRepository(ValoracionDAO valoracionDAO) {
        this.valoracionDAO = valoracionDAO;
    }

    /**
     * Busca todas las valoraciones junto a sus respectivas notas
     *
     * @return Una lista de las producciones con su nota
     * @throws DatabaseErrorException
     * @author Andreu Francés
     */

    public ArrayList<Valoracion> findAll() throws DatabaseErrorException {
        return valoracionDAO.findAll();
    }

    /**
     * Gurada una valoración
     *
     * @param valoracion
     * @throws DatabaseErrorException
     * @author Andreu Francés
     */

    public void save(Valoracion valoracion) throws DatabaseErrorException, NotFoundException {
        valoracionDAO.save(valoracion);
    }



}
