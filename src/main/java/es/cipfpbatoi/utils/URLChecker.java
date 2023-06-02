package es.cipfpbatoi.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLChecker {
    public static boolean checkURL(String urlProporcionada) {
        try {
            URL url = new URL(urlProporcionada); // URL de la imagen

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();

            // Si el código de respuesta es 200, la URL es válida y devuelve información.
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

