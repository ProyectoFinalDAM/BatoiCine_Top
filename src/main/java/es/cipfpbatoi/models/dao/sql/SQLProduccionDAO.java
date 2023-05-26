package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Calificacion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class SQLProduccionDAO implements ProduccionDAO {
    private Connection connection;

    @Override
    public ArrayList<Produccion> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM Produccion");
        connection = new MySqlConnection().conectar();
        ArrayList<Produccion> produccions = new ArrayList<>();

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
        LocalDate fecha_lanzamiento = rs.getDate("fecha_lanzamiento").toLocalDate();
        int duracion = rs.getInt("duracion");
        Set<String> genero = Collections.singleton(rs.getString("genero"));
        String director = rs.getString("director");
        String guion = rs.getString("guion");
        String productora = rs.getString("productora");
        String poster = rs.getString("poster");
        Set<String> plataforma = Collections.singleton(rs.getString("plataforma"));
        int visualizaciones = rs.getInt("visualizaciones");
        String web = rs.getString("web");
        Tipo tipo = Tipo.valueOf(rs.getString("tipo"));

        return new Produccion(id, titulo, calificacion, fecha_lanzamiento, duracion, genero, director, guion, productora, poster, plataforma, visualizaciones, web, tipo);
    }

    @Override
    public void save(Produccion produccion) throws DatabaseErrorException {
        String sql = String.format("INSERT INTO Produccion (id, titulo, calificacion, fecha_lanzamiento, duracion, genero, director, guion, productora, poster, plataforma, visualizaciones, web, tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        connection = new MySqlConnection().conectar();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setString( 1, produccion.getId() );
            preparedStatement.setString( 2, produccion.getTitulo() );
            preparedStatement.setString( 3, produccion.getCalificacion().toString() );
            preparedStatement.setDate( 4, Date.valueOf(produccion.getFecha_lanzamiento()));
            preparedStatement.setInt( 5, produccion.getDuracion() );
            String generosString = String.join(",", produccion.getGenero());
            preparedStatement.setString( 6, generosString);
            preparedStatement.setString( 7, produccion.getDirector());
            preparedStatement.setString( 8, produccion.getGuion());
            preparedStatement.setString( 9, produccion.getProductora());
            preparedStatement.setString( 10, produccion.getPoster() );
            String plataformasString = String.join(",", produccion.getPlataforma());
            preparedStatement.setString( 11, plataformasString);
            preparedStatement.setInt( 12, produccion.getVisualizaciones() );
            preparedStatement.setString( 13, produccion.getWeb() );
            preparedStatement.setString( 14, produccion.getTipo().toString());
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }

    @Override
    public Produccion getById(String id) throws NotFoundException, DatabaseErrorException {
        String sql = String.format("SELECT * FROM Produccion WHERE id = ?");
        connection = new MySqlConnection().conectar();
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
