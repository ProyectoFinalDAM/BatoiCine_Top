package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Calificacion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class SQLProduccionDAO implements ProduccionDAO {

    public static final String IP = "???";

    public static final String DATABASE = "BatoiCine_Top";

    public static final String NOMBRE_USUARIO = "batoi";

    public static final String NOMBRE_TABLA = "";

    private Connection connection;


    @Override
    public ArrayList<Produccion> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);

        ArrayList<Produccion> produccions = new ArrayList<>();
        connection = new MySqlConnection(IP, DATABASE, NOMBRE_USUARIO, "1234").getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Produccion produccion = geProduccionFromResultset(resultSet);
                produccions.add(produccion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return produccions;
    }

    private Produccion geProduccionFromResultset(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String titulo = rs.getString("titulo");
        Calificacion calificacion = Calificacion.valueOf(rs.getString("calificacion"));
        LocalDate fechaLanzamiento = rs.getDate("fechaLanzamiento").toLocalDate();
        int duracion = rs.getInt("duracion");
        int id_genero = rs.getInt("id_genero");
        Set<String> actor = Collections.singleton(rs.getString("actor"));
        String guion = rs.getString("guion");
        String productora = rs.getString("productora");
        String url_trailer = rs.getString("url_trailer");
        String poster = rs.getString("poster");
        Set<String> plataforma = Collections.singleton(rs.getString("plataforma"));
        int visualizaciones = rs.getInt("visualizaciones");
        String web = rs.getString("web");
        Tipo tipo = Tipo.valueOf(rs.getString("tipo"));

        return new Produccion(id, titulo, calificacion, fechaLanzamiento, duracion, id_genero, actor, guion, productora, url_trailer, poster, plataforma, visualizaciones, web, tipo);
    }

    @Override
    public void save(Produccion produccion) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id, titulo, calificacion, fechaLanzamiento, duracion, id_genero, actor, guion, productora, url_trailer, poster, plataforma, visualizaciones, web, tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", NOMBRE_TABLA );
        connection = new MySqlConnection( IP, DATABASE, NOMBRE_USUARIO, "1234" ).getConnection();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setString( 1, produccion.getId() );
            preparedStatement.setString( 2, produccion.getTitulo() );
            preparedStatement.setString( 3, produccion.getCalificacion().toString() );
            preparedStatement.setString( 4, produccion.getFecha_lanzamiento().toString() );
            preparedStatement.setInt( 5, produccion.getDuracion() );
            preparedStatement.setInt( 6, produccion.getId_genero() );
            preparedStatement.setString( 7, produccion.getActores().toString());
            preparedStatement.setString( 8, produccion.getGuion() );
            preparedStatement.setString( 9, produccion.getProductora() );
            preparedStatement.setString( 10, produccion.getUrl_trailer() );
            preparedStatement.setString( 11, produccion.getPoster() );
            preparedStatement.setString( 12, produccion.getPlataforma().toString());
            preparedStatement.setInt( 13, produccion.getVisualizaciones() );
            preparedStatement.setString( 14, produccion.getWeb() );
            preparedStatement.setString( 15, produccion.getTipo().toString());
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }

    @Override
    public Produccion getById(String id) throws NotFoundException, DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", NOMBRE_TABLA);
        connection = new MySqlConnection(IP, DATABASE, NOMBRE_USUARIO, "1234").getConnection();

        try (
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Produccion produccion = geProduccionFromResultset(resultSet);
                if (produccion.getId().equals(id)) {
                    return produccion;
                }
            }

            throw new NotFoundException("No existe la producción con el id: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (select)");
        }
    }

    /*
    public Produccion encontrarPorId(String dni) throws DatabaseErrorException, NotFoundException {
        try {
            return getById(dni);
        } catch (NotFoundException ex) {
            throw new NotFoundException("No ha sido posible encontrar la producción.");
        }
    }

     */

}
