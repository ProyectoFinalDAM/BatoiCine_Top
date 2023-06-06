package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.VisualizarDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;

import java.util.ArrayList;

    /**
     * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
    */

public class VisualizarRepository {
    private VisualizarDAO visualizarDAO;
    private ProduccionRepository produccionRepository;
        private UserRepository userRepository;

    public VisualizarRepository(VisualizarDAO visualizarDAO, ProduccionRepository produccionRepository, UserRepository userRepository) {
        this.visualizarDAO = visualizarDAO;
        this.produccionRepository = produccionRepository;
        this.userRepository = userRepository;
    }

        /**
         * Busca en la base de datos las coincidencias de usuarios que han visto la pelicula pasada como parametro
         * @author Marcos Sanz
         * @param id_produccion
         * @return Retorna una lista de los usuarios que han visualiado la producción
         * @throws DatabaseErrorException
         */

    public ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException{
        ArrayList<User> usuariosActualizados= visualizarDAO.getProdUsers(id_produccion);

        for (User usuaro: usuariosActualizados) {
            try {
                User userOriginal = userRepository.getById(usuaro.getId());
                usuaro.setNombre(userOriginal.getNombre());
                usuaro.setContrasenya(usuaro.getContrasenya());
            } catch (UserNotExistException e) {
                throw new RuntimeException(e);
            }
        }

        return usuariosActualizados;
    }

        /**
         * Busca en la base de datos las coincidencias de producciones que ha visto cierto usuario
         * @param id_user
         * @return Retorna una lista de las producciones que ha visualiado el usuario
         * @throws DatabaseErrorException
         */

    public ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException{
        ArrayList<Produccion> produccionesActualizadas= visualizarDAO.getUserProducciones(id_user);

        for (Produccion prod: produccionesActualizadas) {
            try {
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
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        return produccionesActualizadas;
    }

        /**
         * Guarda en la base de datos una visualizacion
         * @author Marcos Sanz
         * @param visualizar
         * @throws DatabaseErrorException
         */
    public void save(Visualizar visualizar) throws DatabaseErrorException{
        visualizarDAO.save(visualizar);
    }
}
