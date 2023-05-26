package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.EsFavoritaDAO;
import es.cipfpbatoi.models.dao.sql.SQLEsFavoritaDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public class EsFavoritaRepository {
    private EsFavoritaDAO esFavoritaDAO;
    private ProduccionRepository produccionRepository;
    private UserRepository userRepository;

    public EsFavoritaRepository(EsFavoritaDAO esFavoritaDAO, ProduccionRepository produccionRepository, UserRepository userRepository) {
        this.esFavoritaDAO = esFavoritaDAO;
        this.produccionRepository = produccionRepository;
        this.userRepository = userRepository;
    }

    public ArrayList<Produccion> getUserFavs(User user) throws DatabaseErrorException, NotFoundException {
        ArrayList<Produccion> produccionesActualizadas= esFavoritaDAO.getUserFavs(user);

        for (Produccion prod: produccionesActualizadas) {
            Produccion produccionOrig = produccionRepository.getById(prod.getId());
            prod.setTitulo(produccionOrig.getTitulo());
            prod.setCalificacion(produccionOrig.getCalificacion());
            prod.setFecha_lanzamiento(produccionOrig.getFecha_lanzamiento());
            prod.setDuracion(produccionOrig.getDuracion());
            prod.setGenero(produccionOrig.getGenero());
            prod.setDirector(produccionOrig.getDirector());
            prod.setGuion(produccionOrig.getGuion());
            prod.setProductora(produccionOrig.getProductora());
            prod.setPoster(produccionOrig.getPoster());
            prod.setPlataforma(produccionOrig.getPlataforma());
            prod.setVisualizaciones(produccionOrig.getVisualizaciones());
            prod.setWeb(produccionOrig.getWeb());
            prod.setTipo(produccionOrig.getTipo());
        }

        return produccionesActualizadas;
    }

    public ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException, UserNotExistException {
        ArrayList<User> usuariosActualizados= esFavoritaDAO.getProdUsers(produccion);

        for (User usuaro: usuariosActualizados) {
            User userOriginal = userRepository.getById(usuaro.getId());
            usuaro.setNombre(userOriginal.getNombre());
            usuaro.setContrasenya(usuaro.getContrasenya());
        }

        return usuariosActualizados;
    }
    public void save(User user, Produccion produccion){
        esFavoritaDAO.save(user,produccion);
    }
}
