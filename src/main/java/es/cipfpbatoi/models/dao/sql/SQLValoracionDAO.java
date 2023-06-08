package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLValoracionDAO implements ValoracionDAO {

    public static final String NOMBRE_TABLA = "Valora";

    private Connection connection;

    public SQLValoracionDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    /**
     * Busca todas las valoraciones junto a sus respectivas notas
     * @author Pablo Marin
     * @return Una lista de las producciones con su nota
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<Valoracion> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);

        ArrayList<Valoracion> valoracions = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Valoracion valoracion = geValoracionFromResultset(resultSet);
                valoracions.add(valoracion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos");
        }

        return valoracions;
    }

    /**
     * Convierte los campos string a Valoración
     * @author Pablo Marín
     * @param rs
     * @return retorna una valoración
     * @throws SQLException
     */

    private Valoracion geValoracionFromResultset(ResultSet rs) throws SQLException {
        String id_produccion = rs.getString("id_produccion");
        String id_usuario = rs.getString("id_usuario");
        int nota = rs.getInt("nota");
        String comentario = rs.getString("comentario");

        return new Valoracion(id_produccion, id_usuario, nota, comentario);
    }

    /**
     * Gurada una valoración
     * @author Pablo Marin
     * @param valoracion
     * @throws DatabaseErrorException
     */

    @Override
    public void save(Valoracion valoracion) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id_produccion, id_usuario, nota, comentario) VALUES (?,?,?,?)", NOMBRE_TABLA );

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setString( 1, valoracion.getId_produccion() );
            preparedStatement.setString( 2, valoracion.getId_usuario() );
            preparedStatement.setInt( 3, valoracion.getNota() );
            preparedStatement.setString( 4, valoracion.getComentario() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
    }
}
