package es.cipfpbatoi.exception;

public class UserNotExistException extends Exception{

    /**
     * Muestra la excepción con el mensaje de que el usuario o contraseña no existe
     * @author Marcos Sanz
     */

    public UserNotExistException() {
        super("El usuario/contraseña no existe.");
    }
}
