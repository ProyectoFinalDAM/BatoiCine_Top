package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> findAll();
    void save(User user);
    boolean validUser(String name, String password);
}
