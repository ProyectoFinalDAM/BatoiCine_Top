package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.VisualizarDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;

import java.util.ArrayList;

public class VisualizarRepository {
    private VisualizarDAO visualizarDAO;

    public VisualizarRepository(VisualizarDAO visualizarDAO) {
        this.visualizarDAO = visualizarDAO;
    }
    public ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException{
        return visualizarDAO.getProdUsers(produccion);
    }

    public ArrayList<User> getProdProducciones(Produccion produccion) throws DatabaseErrorException{
        return visualizarDAO.getProdProducciones(produccion);
    }
    public void save(Visualizar visualizar) throws DatabaseErrorException{
        visualizarDAO.save(visualizar);
    }
}
