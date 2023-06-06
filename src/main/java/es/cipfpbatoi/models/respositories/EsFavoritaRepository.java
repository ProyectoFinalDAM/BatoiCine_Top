package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.EsFavoritaDAO;
import es.cipfpbatoi.models.dao.sql.SQLEsFavoritaDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

/**
 * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
 */

public class EsFavoritaRepository {
    private EsFavoritaDAO esFavoritaDAO;
    private ProduccionRepository produccionRepository;
    private UserRepository userRepository;

    public EsFavoritaRepository(EsFavoritaDAO esFavoritaDAO, ProduccionRepository produccionRepository, UserRepository userRepository) {
        this.esFavoritaDAO = esFavoritaDAO;
        this.produccionRepository = produccionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Recibe un usuario para recibir una lista de sus producciones favoritas
     * @author Marcos Sanz
     * @param user
     * @return ArrayList de producciones
     * @throws DatabaseErrorException
     * @throws NotFoundException
     */
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

    /**
     * Busca a través de la producción proporcionada los usuario que la tienen en favoritas o los actualiza
     * @author Marcos Sanz
     * @param produccion
     * @return ArrayList de usuarios
     * @throws DatabaseErrorException
     * @throws UserNotExistException
     */

    public ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException, UserNotExistException {
        ArrayList<User> usuariosActualizados= esFavoritaDAO.getProdUsers(produccion);

        for (User usuaro: usuariosActualizados) {
            User userOriginal = userRepository.getById(usuaro.getId());
            usuaro.setNombre(userOriginal.getNombre());
            usuaro.setContrasenya(usuaro.getContrasenya());
        }

        return usuariosActualizados;
    }

    /**
     * Registra en la lista del usuario las producciones que ha seleccionado como favorita
     * @author Marcos Sanz
     * @param user
     * @param produccion
     */
    public void save(User user, Produccion produccion){
        esFavoritaDAO.save(user,produccion);
    }

    /**
     * ELimina en la lista del usuario las producciones que ha seleccionado como favorita
     * @author Andreu Francés
     * @param user
     * @param produccion
     * @throws DatabaseErrorException
     */
    public void eliminar(User user, Produccion produccion) throws DatabaseErrorException{
        esFavoritaDAO.eliminar(user, produccion);
    }
}
