package es.cipfpbatoi;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.sql.SQLProduccionDAO;
import es.cipfpbatoi.models.dao.sql.SQLTemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;
import es.cipfpbatoi.utils.Validator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BatoiCIneTopPrueba {

    public BatoiCIneTopPrueba() {
        System.out.println("-- Pruebas de la apliación --");
    }

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll => setup(); Se ejecuta ANTES de empezar las pruebas");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll => tear(); Se ejecuta al final de todas las pruebas");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach => setupThis(); Se ejcuta ANTES de cada prueba");
    }

    @AfterEach
    void tearThis(){
        System.out.println("AfterEach => tearThis(); Se ejecuta DESPUÉS de cada prueba");
    }

    @Test
    void contrasenyaCorrecta(){
        boolean result1 = Validator.PASSWORD_REGEXP.matches("MiContraseña@12");
        assertTrue(result1);

        boolean result2 = Validator.PASSWORD_REGEXP.matches("abc");
        assertFalse(result2);

        boolean result3 = Validator.PASSWORD_REGEXP.matches("MiContraseña15");
        assertFalse(result3);

        boolean result4 = Validator.PASSWORD_REGEXP.matches("12@m");
        assertFalse(result4);
    }
    @Test
    void obtenerProduccionCorrecto(){
        SQLProduccionDAO sqlProduccionDAO= new SQLProduccionDAO();
        Produccion prod= sqlProduccionDAO.getCoincidenciaTitulo("Argo");

        String result1 = prod.getDirector();
        assertEquals("Ben Affleck", result1);

        String result2 = prod.getDirector();
        assertNotEquals("Marcos Sanz", result2);

    }
    @Test
    void obtenerTemporadaCorrecta(){
        SQLTemporadaDAO sqlTemporadaDAO= new SQLTemporadaDAO();
        try {
            Temporada temporada= sqlTemporadaDAO.getByIdProdTemporada("145",1);
            int result1 = temporada.getAnyoLanzamiento();
            assertTrue(result1==2017);
        } catch (DatabaseErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Temporada temporada= sqlTemporadaDAO.getByIdProdTemporada("146",2);
            String result1 = temporada.getGuion();
            assertFalse(result1.equals("Ejemplo"));
        } catch (DatabaseErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
