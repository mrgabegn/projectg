package br.com.credsystem.common.util;
import static javax.swing.table.DefaultTableCellRenderer.*;

public class StringUtils {

    public static String getTextoAlinhado(String texto, int tamanho, int alinhamento) {
        StringBuilder retorno = new StringBuilder(tamanho);
        retorno.append(stringOfChar(' ', tamanho));
        texto = (texto == null ? "" : texto);
        try {
            if (texto.length() >= tamanho) {
                retorno.replace(0, tamanho, texto.substring(0, tamanho));
            } else {
                switch (alinhamento) {
                    case CENTER:
                        int inicio = ((tamanho - texto.length()) / 2);
                        retorno.replace(inicio, texto.length() + inicio, texto);
                        break;
                    case LEFT:
                        retorno.replace(0, tamanho, texto + (stringOfChar(' ', tamanho - texto.length())));
                        break;
                    default:
                        retorno.replace((tamanho - texto.length()), tamanho, texto);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno.toString();
    }

    public static String stringOfChar(char c, int qtd) {
        StringBuilder str = new StringBuilder(qtd);
        for (int i = 0; i < qtd; ++i) {
            str.append(c);
        }
        return str.toString();
    }

    public static String zerosLeft(String str, int len) {
        String strAux = str.trim();
        int lenAux = strAux.length();
        if (len > lenAux) {
            return stringOfChar('0', len - lenAux) + strAux;
        } else if (len < lenAux) {
            return strAux.substring(lenAux - len, lenAux);
        } else {
            return strAux;
        }
    }

    public static String completeZerosLeft(String str, int len) {
        String strAux = str.trim();
        int lenAux = strAux.length();
        if (len > lenAux) {
            return stringOfChar('0', len - lenAux) + strAux;
        } else if (len < lenAux) {
            return strAux.substring(0, len);
        } else {
            return strAux;
        }
    }
}
