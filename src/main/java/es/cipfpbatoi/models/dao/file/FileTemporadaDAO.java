package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.TemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;
import es.cipfpbatoi.utils.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileTemporadaDAO implements TemporadaDAO {

    private static final String FILE_DATABASE = "resources/database/extra_series.csv";

    private static final int PELICULA = 0;

    private static final int TEMPORADA = 1;

    private static final int ANYO_LANZAMIENTO = 2;

    private static final int GUION = 3;

    private static final int CAPITULOS = 4;

    private static final String FIELD_SEPARATOR = ";";
    private              File   file;
    public FileTemporadaDAO() {
        this.file = new File( FILE_DATABASE );
    }

    @Override
    public ArrayList<Temporada> findAll() {
        ArrayList<Temporada> temporadas = new ArrayList<>();
        try {
            try (BufferedReader bufferedReader = getReader()) {
                bufferedReader.readLine();
                do {
                    String register = bufferedReader.readLine();
                    if (register == null) {
                        return temporadas;
                    }
                    temporadas.add( getTemporadaFromRegister(register));
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return temporadas;
    }

    @Override
    public void save(Temporada temporada) throws DatabaseErrorException {

    }

    private Temporada getTemporadaFromRegister(String register) {
        String[] fields = register.split( Validator.REGEX_TEMPORADAS );
        int pelicula = Integer.parseInt(fields[PELICULA]);
        int temporada = Integer.parseInt(fields[TEMPORADA]);
        int anyo =  Integer.parseInt(fields[ANYO_LANZAMIENTO]);
        String guion = fields[GUION];
        int capitulos = Integer.parseInt(fields[CAPITULOS]);
        return new Temporada( pelicula, temporada, anyo, guion, capitulos );
    }

    private BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(file));
    }

}
