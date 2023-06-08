package es.cipfpbatoi;

import es.cipfpbatoi.utils.Validator;
import org.junit.jupiter.api.*;

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
}
