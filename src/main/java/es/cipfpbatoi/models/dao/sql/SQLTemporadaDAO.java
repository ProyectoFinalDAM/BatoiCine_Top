package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.TemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Temporada;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLTemporadaDAO implements TemporadaDAO {
    public static final String NOMBRE_TABLA = "Temporada";
    private Connection connection;

    public SQLTemporadaDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    /**
     * Busca todos los elementos de las temporadas
     * @author Andreu Francés
     * @return Una lista de todos los episodios de la temporada
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<Temporada> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);
        ArrayList<Temporada> temporadas = new ArrayList<>();

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Temporada temporada = getTemporadaFromResultset(resultSet);
                temporadas.add(temporada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return temporadas;
    }

    /**
     * A través de los campos string los convierte en un objeto temporada
     * @author Andreu Francés
     * @param rs
     * @return retorna una temporada
     * @throws SQLException
     */

    private Temporada getTemporadaFromResultset(ResultSet rs) throws SQLException {
        String pelicula = rs.getString("id_produccion");
        int temporada = rs.getInt("temporada");
        int anyoLanzamiento = rs.getInt("anyo_lanzamiento");
        String guion = rs.getString("plot");
        int capitulos = rs.getInt("capitulos");

        return new Temporada(pelicula, temporada, anyoLanzamiento,guion,capitulos);
    }

    /**
     * Gurada en la base de datos todos los datos sobre las temporadas
     * @author Andreu Francés
     * @param temporada
     * @throws DatabaseErrorException
     */

    @Override
    public void save(Temporada temporada) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id_produccion, temporada, anyo_lanzamiento, plot, capitulos) VALUES (?,?,?,?,?)", NOMBRE_TABLA );

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString( 1, temporada.getPelicula() );
            preparedStatement.setInt( 2, temporada.getTemporada() );
            preparedStatement.setInt( 3,temporada.getAnyoLanzamiento());
            preparedStatement.setString( 4, temporada.getGuion() );
            preparedStatement.setInt( 5, temporada.getCapitulos() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }
    /**
     * Ontiene una temporada filtrada por su id_produccion y el numero de temporada
     * @author Marcos Sanz
     * @param id_produccion
     * @param temporada
     * @return retorna una temporada filtrada
     * @throws DatabaseErrorException
     * @throws NotFoundException
     */
    @Override
    public Temporada getByIdProdTemporada(String id_produccion, int temporada) throws DatabaseErrorException, NotFoundException {
        String sql = String.format( "SELECT * FROM %s WHERE id_produccion=? AND temporada=?", NOMBRE_TABLA );

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString( 1, id_produccion );
            preparedStatement.setInt( 2, temporada);
            ResultSet resultSet= preparedStatement.executeQuery();


            while (resultSet.next()) {
                return getTemporadaFromResultset(resultSet);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
        throw new NotFoundException("No existe temporada.");
    }

}
