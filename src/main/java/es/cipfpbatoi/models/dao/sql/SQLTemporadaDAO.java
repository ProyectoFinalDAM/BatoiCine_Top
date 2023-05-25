package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.TemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Calificacion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class SQLTemporadaDAO implements TemporadaDAO {
    public static final String NOMBRE_TABLA = "Temporada";

    private Connection connection;
    @Override
    public ArrayList<Temporada> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);
        connection = new MySqlConnection().conectar();
        ArrayList<Temporada> temporadas = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Temporada temporada = geTemporadaFromResultset(resultSet);
                temporadas.add(temporada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return temporadas;
    }

    private Temporada geTemporadaFromResultset(ResultSet rs) throws SQLException {
        int pelicula = rs.getInt("id_produccion");
        int temporada = rs.getInt("temporada");
        int anyoLanzamiento = rs.getInt("anyo_lanzamiento");
        String guion = rs.getString("plot");
        int capitulos = rs.getInt("capitulos");


        return new Temporada(pelicula, temporada, anyoLanzamiento,guion,capitulos);
    }

    @Override
    public void save(Temporada temporada) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id_produccion, temporada, anyo_lanzamiento, plot, duracion, capitulos) VALUES (?,?,?,?,?,?)", NOMBRE_TABLA );
        connection = new MySqlConnection().conectar();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setInt( 1, temporada.getPelicula() );
            preparedStatement.setInt( 2, temporada.getTemporada() );
            preparedStatement.setInt( 3, temporada.getAnyoLanzamiento());
            preparedStatement.setString( 4, temporada.getGuion() );
            preparedStatement.setInt( 5, temporada.getCapitulos() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }
}
