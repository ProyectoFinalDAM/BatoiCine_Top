package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

    /**
     * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
     */

public class ProduccionRepository {
    private ProduccionDAO produccionDAO;

    public ProduccionRepository(ProduccionDAO produccionDAO) {
        this.produccionDAO = produccionDAO;
    }

        /**
         * Busca todos las producciones de la base de datos
         * @author Marcos Sanz
         * @return Una lista de todas las producciones
         * @throws DatabaseErrorException
         */

    public ArrayList<Produccion> findAll() throws DatabaseErrorException{
        return this.produccionDAO.findAll();
    }

        /**
         * Busca todas las producciones de la base de datos que coinciden con el tipo
         * @author Martin Peidro
         * @param tipo
         * @return Una lista de todas las producciones que coinciden con el tipo
         * @throws DatabaseErrorException
         */

    public ArrayList<Produccion> findAll(String tipo) throws DatabaseErrorException{
        return this.produccionDAO.findAll(tipo);
    }

        /**
         * Guarda en la base de datos la producción pasada como parametro
         * @author Marcos Sanz
         * @param produccion
         * @throws DatabaseErrorException
         */

    public void save(Produccion produccion) throws DatabaseErrorException{
        this.produccionDAO.save(produccion);
    }

        /**
         * Recoge una producción que coincida con el id pasado como parametro
         * @author Marcos Sanz
         * @param id
         * @return Una producción
         * @throws DatabaseErrorException
         * @throws NotFoundException
         */

    public Produccion getById (String id) throws NotFoundException, DatabaseErrorException{
        return this.produccionDAO.getById(id);
    }

        /**
         * Recoge las cinco películas mejor valoradas
         * @author Marcos Sanz
         * @return Una lista de las cinco películas mejor valoradas
         * @throws DatabaseErrorException
         */

    public ArrayList<Produccion> getRecommendedFilms() throws DatabaseErrorException{
        return this.produccionDAO.getRecommendedFilms();
    }

        /**
         * Recoge las cinco series mejor valoradas
         * @author Marcos Sanz
         * @return Una lista de las cinco series mejor valoradas
         * @throws DatabaseErrorException
         */

    public ArrayList<Produccion> getRecommendedSeries() throws DatabaseErrorException{
        return this.produccionDAO.getRecommendedSeries();
    }

        /**
         * Busca la producción de la base de datos que coincide con el titulo
         * @author Martín Peidro
         * @param titulo
         * @return Una producción que coincide con el título
         */

        public Produccion getCoincidenciaTitulo(String titulo){
        return this.produccionDAO.getCoincidenciaTitulo( titulo );
    }

        /**
         * Busca tados las producciones de la base de datos que coinciden con el genero
         * @author Martín Peidro
         * @param genero
         * @return Una lista de todas las producciones que coinciden con el genero
         */


        public ArrayList<Produccion> getCoincidenciaGenero(Genero genero){
        return this.produccionDAO.getCoincidenciaGenero( genero );
    }

        /**
         * Busca todas las producciones de la base de datos que coinciden con el titulo y genero
         * @author Martín Peidro
         * @param genero
         * @param titulo
         * @return Una lista de todas las producciones que coinciden con el titulo y genero
         */

    public ArrayList<Produccion> getCoincidenciaGeneroTitulo(String titulo, Genero genero) {
        return produccionDAO.getCoincidenciaGeneroTitulo(titulo, genero);
    }
    public ArrayList<String> get10Direcotores(){
        return produccionDAO.get10Direcotores();
    }
    public ArrayList<String> getPlataformas(){
        return produccionDAO.getPlataformas();
    }
    public ArrayList<String> getCalificaciones(){
        return produccionDAO.getCalificaciones();
    }
    public ArrayList<Produccion> getClasDirPlat(String seleccion1, String seleccion2, String seleccion3, String columnaOrdenamiento){
        return produccionDAO.getClasDirPlat(seleccion1, seleccion2, seleccion3, columnaOrdenamiento);
    }

    public ArrayList<Produccion> getClasDir(String seleccion1, String seleccion2, String columnaOrdenamiento){
        return produccionDAO.getClasDir(seleccion1, seleccion2, columnaOrdenamiento);
    }

    public ArrayList<Produccion> getClasPlat(String seleccion1, String seleccion2, String columnaOrdenamiento){
        return produccionDAO.getClasPlat(seleccion1, seleccion2, columnaOrdenamiento);
    }

    public ArrayList<Produccion> getDirPlat(String seleccion1, String seleccion2, String columnaOrdenamiento){
        return produccionDAO.getDirPlat(seleccion1, seleccion2, columnaOrdenamiento);
    }

    public ArrayList<Produccion> getUnFiltrado(String seleccion1, String seleccion2, String columnaOrdenamiento){
        return produccionDAO.getUnFiltrado(seleccion1, seleccion2, columnaOrdenamiento);
    }
    public ArrayList<Produccion> getOrdenacion(String columnaOrdenamiento){
        return produccionDAO.getOrdenacion(columnaOrdenamiento);
    }
}
