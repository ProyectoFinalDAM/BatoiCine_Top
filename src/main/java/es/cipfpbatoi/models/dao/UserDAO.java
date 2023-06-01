package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> findAll();
    void save(User user);
    int getLastCod();
    User getById(int id) throws UserNotExistException;
    boolean validUser(String name, String password);
    User getUser(String name, String password) throws UserNotExistException;

}
