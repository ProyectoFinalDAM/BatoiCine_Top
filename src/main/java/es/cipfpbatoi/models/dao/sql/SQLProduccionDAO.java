package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Calificacion;
import es.cipfpbatoi.models.dto.prods.Genero;
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

    public SQLProduccionDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    @Override
    public ArrayList<Produccion> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM Produccion");
        ArrayList<Produccion> produccions = new ArrayList<>();

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

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

    @Override
    public ArrayList<Produccion> getRecommendedFilms() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM Ranking INNER JOIN Produccion ON id_produccion=Produccion.id WHERE tipo='movie' ORDER BY puntos DESC");
        ArrayList<Produccion> produccions = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

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

    @Override
    public ArrayList<Produccion> getRecommendedSeries() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM Ranking INNER JOIN Produccion ON id_produccion=Produccion.id WHERE tipo='tv-show' ORDER BY puntos DESC");
        ArrayList<Produccion> produccions = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

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

    @Override
    public ArrayList<Produccion> findAll(String tipo) throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM Produccion WHERE tipo=?");
        ArrayList<Produccion> produccions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString(1, tipo);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        Calificacion calificacion;
        if (rs.getString("calificacion").equals("PG-13")){
            calificacion = Calificacion.PG13;
        } else {
            calificacion = Calificacion.valueOf(rs.getString("calificacion"));

        }
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
        Tipo tipo = null;
        if (rs.getString("tipo").equals("movie")){
            tipo= Tipo.MOVIE;
        } else if (rs.getString("tipo").equals("tv-show")) {
            tipo= Tipo.TVSHOW;
        }

        return new Produccion(id, titulo, calificacion, fecha_lanzamiento, duracion, genero, director, guion, productora, poster, plataforma, visualizaciones, web, tipo);
    }

    @Override
    public void save(Produccion produccion) throws DatabaseErrorException {
        String sql = String.format("INSERT INTO Produccion (id, titulo, calificacion, fecha_lanzamiento, duracion, genero, director, guion, productora, poster, plataforma, visualizaciones, web, tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
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
        String sql = String.format("SELECT * FROM Produccion WHERE id=?");

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

    @Override
    public String getPortadaProduccion(Produccion produccion) throws DatabaseErrorException {
        String sql = String.format("SELECT poster FROM Produccion WHERE id=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
             ResultSet resultSet = preparedStatement.executeQuery(sql)) {
            preparedStatement.setString(1, produccion.getId());
            while (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }
        return null;
    }

    @Override
    public Produccion getCoincidenciaTitulo(String text) {
        String sql =  String.format( "SELECT * FROM Produccion WHERE titulo=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )){
            preparedStatement.setString(1, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return geProduccionFromResultset( resultSet );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Produccion> getCoincidenciaGenero(Genero genero) {
        ArrayList<Produccion> produccions = new ArrayList<>();
        String sql =  String.format( "SELECT * FROM Produccion WHERE genero=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
             ResultSet resultSet = preparedStatement.executeQuery(sql)){
            preparedStatement.setString(1, genero.getDescripcion());

            while (resultSet.next()) {
                produccions.add( geProduccionFromResultset( resultSet ) );
            }

            return produccions;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

