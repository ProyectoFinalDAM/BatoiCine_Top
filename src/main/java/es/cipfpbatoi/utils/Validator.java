package es.cipfpbatoi.utils;

public class Validator {

    /**
     * Contiene strings para comprobar ciertos campos de texto
     */
    public static final String REGEX_TEMPORADAS = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{5,20}$";
}
