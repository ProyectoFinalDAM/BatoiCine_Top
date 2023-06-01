package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.models.dao.GeneroDAO;
import es.cipfpbatoi.models.dto.prods.Genero;

import java.util.ArrayList;

public class GeneroRepository {
    private GeneroDAO generoDAO;

    public GeneroRepository(GeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }
    public ArrayList<Genero> findAll(){
        return generoDAO.findAll();
    }
    public void save(Genero genero){
        generoDAO.save(genero);
    }
}
