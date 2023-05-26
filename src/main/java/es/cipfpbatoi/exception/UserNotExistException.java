package es.cipfpbatoi.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException() {
        super("El usuario/contraseña no existe.");
    }
}
