package es.cipfpbatoi.exception;

public class UserAlreadyExistsException extends Exception{


    /**
     * Muestra la excepción con el mensaje de usuario existente
     * @author Marcos Sanz
     */
    public UserAlreadyExistsException() {
        super("El usuario ya existe.");
    }
}
