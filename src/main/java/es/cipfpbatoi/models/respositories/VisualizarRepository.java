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
    public ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException{
        return visualizarDAO.getProdUsers(id_produccion);
    }

    ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException{
        return visualizarDAO.getUserProducciones(id_user);
    }
    public void save(Visualizar visualizar) throws DatabaseErrorException{
        visualizarDAO.save(visualizar);
    }
}
