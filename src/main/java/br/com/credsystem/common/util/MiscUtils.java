package br.com.credsystem.common.util;

public class MiscUtils {

    public static String soNumero(String numero) {
        return filtra(numero, "0123456789");
    }

    public static String filtra(String str, String filtro) {
        String validos = filtro;
        String strs = "";
        char digito;
        if (str == null) {
            return "0";
        }
        for (int i = 0; i < str.length(); i++) {
            digito = str.charAt(i);
            if (validos.indexOf(digito) >= 0) {
                strs += digito;
            }
        }
        return strs;
    }
}
