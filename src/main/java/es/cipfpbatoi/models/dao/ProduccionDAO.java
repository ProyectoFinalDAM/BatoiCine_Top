package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ProduccionDAO {


    /**
     * Busca todas las producciones de la base de datos
     * @author Pablo Marin
     * @return Una lista de todas las producciones
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> findAll() throws DatabaseErrorException;

    /**
     * Recoge las cinco películas mejor valoradas
     * @author Marcos Sanz
     * @return Una lista de las cinco películas mejor valoradas
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> getRecommendedFilms() throws DatabaseErrorException;

    /**
     * Recoge las cinco series mejor valoradas
     * @author Marcos Sanz
     * @return Una lista de las cinco series mejor valoradas
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> getRecommendedSeries() throws DatabaseErrorException;

    /**
     * Busca todas las producciones de la base de datos que coinciden con el tipo
     * @author Marcos Sanz
     * @param tipo
     * @return Una lista de todas las producciones que coinciden con el tipo
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> findAll(String tipo) throws DatabaseErrorException;

    /**
     * Guarda en la base de datos la producción pasada como parametro
     * @author Pablo Marin
     * @param produccion
     * @throws DatabaseErrorException
     */

    void save(Produccion produccion) throws DatabaseErrorException;

    /**
     * Recoge una producción que coincida con el id pasado como parametro
     * @author Marcos Sanz
     * @param id
     * @return Una producción
     * @throws DatabaseErrorException
     * @throws NotFoundException
     */

    Produccion getById (String id) throws NotFoundException, DatabaseErrorException;

    /**
     * Coge el url de la producción y lo convierte a string
     * @author Marcos Sanz
     * @param produccion
     * @return Un string del url de la portada de la producción
     * @throws DatabaseErrorException
     */

    String getPortadaProduccion(Produccion produccion) throws DatabaseErrorException;

    /**
     * Busca la producción de la base de datos que coincide con el titulo
     * @author Martín Peidro
     * @param text
     * @return Una producción que coincide con el título
     */

    Produccion getCoincidenciaTitulo(String text);

    /**
     * Busca todas las producciones de la base de datos que coinciden con el genero
     * @author Martín Peidro
     * @param genero
     * @return Una lista de todas las producciones que coinciden con el genero
     */

    ArrayList<Produccion> getCoincidenciaGenero(Genero genero);

    /**
     * Busca todas las producciones de la base de datos que coinciden con el titulo y genero
     * @author Martín Peidro
     * @param genero
     * @param titulo
     * @return Una lista de todas las producciones que coinciden con el titulo y genero
     */

    ArrayList<Produccion> getCoincidenciaGeneroTitulo(String titulo, Genero genero);
    /**
     * Obtener todas las calificaciones de las producciones
     * @author Marcos Sanz
     * @return Una lista de Strings de las calificaciones las producciones
     */
    ArrayList<String> getCalificaciones();
    /**
     * Obtener todas las platadormas disponibles de las producciones
     * @author Marcos Sanz
     * @return Una lista de Strings de las platadormas donde se emiten las producciones
     */
    ArrayList<String> getPlataformas();
    /**
     * Obtener los 10 ultimos directores de la BBDD
     * @author Marcos Sanz
     * @return Una lista de Strings de 10 directores
     */
    ArrayList<String> get10Direcotores();
    /**
     * Obtener producciones filtradas por calificacion, director y plataforma. Tambien ordenarlo por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param seleccion1
     * @param seleccion2
     * @param seleccion3
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones filtradas
     */
    ArrayList<Produccion> getClasDirPlat(String seleccion1, String seleccion2, String seleccion3, String columnaOrdenamiento);
    /**
     * Obtener producciones filtradas por calificacion y director. Tambien ordenarlo por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param seleccion1
     * @param seleccion2
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones filtradas
     */
    ArrayList<Produccion> getClasDir(String seleccion1, String seleccion2, String columnaOrdenamiento);
    /**
     * Obtener producciones filtradas por calificacion y plataforma. Tambien ordenarlo por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param seleccion1
     * @param seleccion2
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones filtradas
     */
    ArrayList<Produccion> getClasPlat(String seleccion1, String seleccion2, String columnaOrdenamiento);
    /**
     * Obtener producciones filtradas por director y plataforma. Tambien ordenarlo por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param seleccion1
     * @param seleccion2
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones filtradas
     */
    ArrayList<Produccion> getDirPlat(String seleccion1, String seleccion2, String columnaOrdenamiento);
    /**
     * Obtener producciones filtradas por un tipo de filtrado que se le pasa. Tambien ordenarlo por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param columnaFiltro
     * @param patron
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones filtradas
     */
    ArrayList<Produccion> getUnFiltrado(String columnaFiltro, String patron, String columnaOrdenamiento);
    /**
     * Obtener producciones ordenado por el parámetro que se le pase.
     * @author Marcos Sanz
     * @param columnaOrdenamiento
     * @return Una lista de Strings de las las producciones ordenadas
     */
    ArrayList<Produccion> getOrdenacion(String columnaOrdenamiento);
}
