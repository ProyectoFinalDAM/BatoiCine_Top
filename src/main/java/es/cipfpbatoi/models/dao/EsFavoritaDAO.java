package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface EsFavoritaDAO {
    ArrayList<Produccion> getUserFavs(User user) throws DatabaseErrorException;
    ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException;
    void save(User user, Produccion produccion);

}
