package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ActorDAO;
import es.cipfpbatoi.models.dto.prods.Actor;
import es.cipfpbatoi.models.dto.prods.Genero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileActorDAO implements ActorDAO {

    private static final String FILE_DATABASE = "resources/database/Actores.txt";

    private static final int ID_ACTOR = 0;

    private static final int NOMBRE = 1;

    private static final String FIELD_SEPARATOR = ";";
    private File file;


    public FileActorDAO() {
        this.file = new File( FILE_DATABASE );
    }

    /**
     * Gurada un actor
     * @author Pablo Marin
     * @param actor
     * @throws DatabaseErrorException
     */

    @Override
    public void save(Actor actor) throws DatabaseErrorException {
        //No se usa el save.
    }

    /**
     * Busca todos los actores
     * @author Pablo Marin
     * @return Una lista de los actores
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<Actor> findAll(){
        ArrayList<Actor> autores = new ArrayList<>();
        try {
            try (BufferedReader bufferedReader = getReader()) {
                bufferedReader.readLine();
                do {
                    String register = bufferedReader.readLine();
                    if (register == null) {
                        return autores;
                    }
                    autores.add(getActorFromRegister(register));
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return autores;
    }

    /**
     * Convierte los campos String a un objeto Actor
     * author Pablo Marin
     * @param register
     * @return retorna una temporada
     */

    private Actor getActorFromRegister(String register) {
        String[] fields = register.split(FIELD_SEPARATOR);

        String id = fields[ID_ACTOR];
        String nombre = fields[NOMBRE];

        return new Actor( id, nombre );
    }

    /**
     * Creas un buffered reader con el nombre del archivo dentro
     * @author Pablo Marin
     * @return BufferedReader
     * @throws IOException
     */

    private BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(file));
    }
}
