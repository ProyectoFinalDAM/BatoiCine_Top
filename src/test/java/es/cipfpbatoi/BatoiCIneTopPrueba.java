package es.cipfpbatoi;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.sql.SQLGeneroDAO;
import es.cipfpbatoi.models.dao.sql.SQLProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.utils.Validator;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void consultaGenero(){
        SQLGeneroDAO validator = new SQLGeneroDAO();

        boolean result1 = false;

        for ( Genero genero: validator.findAll()) {
            if ( genero.equals( new Genero( 20, "Action", "Acción" ) ) ){
                result1 = true;
            }
        }
        assertTrue( result1 );

        boolean result2 = true;

        for ( Genero genero: validator.findAll()) {
            if ( genero.equals( new Genero( 38, "X", "XXX" ) ) ){
                result2 = false;
            }
        }
        assertFalse( result2 );

    }
}
