package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ActuaDAO;
import es.cipfpbatoi.models.dto.prods.Actua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileActuaDAO implements ActuaDAO {

    private static final String FILE_DATABASE = "resources/database/Actua.txt";

    private static final int ID_ACTOR = 0;

    private static final int ID_PRODUCCION = 1;

    private static final String FIELD_SEPARATOR = ";";
    private File file;


    public FileActuaDAO() {
        this.file = new File( FILE_DATABASE );
    }

    @Override
    public void save(Actua actua) {
        //No se usa el save.
    }

    /**
     * Busca todas las Actuaciones
     * @author Pablo Marin
     * @return Una lista de las actuaciones
     */

    @Override
    public ArrayList<Actua> findAll(){
        ArrayList<Actua> actua = new ArrayList<>();
        try {
            try (BufferedReader bufferedReader = getReader()) {
                bufferedReader.readLine();
                do {
                    String register = bufferedReader.readLine();
                    if (register == null) {
                        return actua;
                    }
                    actua.add(getActuaFromRegister(register));
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return actua;
    }


    /**
     * Convierte los campos String a un objeto Actua
     * @author Pablo Marin
     * @param register
     * @return retorna una temporada
     */

    private Actua getActuaFromRegister(String register) {
        String[] fields = register.split(FIELD_SEPARATOR);

        String id_actor = fields[ID_ACTOR];
        String id_produccion = fields[ID_PRODUCCION];

        return new Actua( id_actor, id_produccion );
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
