package br.com.credsystem.common.util;

import java.text.NumberFormat;

import static br.com.credsystem.common.util.DateUtils.localeBR;

public class NumUtils {

    public static String formataMoeda(double dValor) {
        NumberFormat formate = NumberFormat.getCurrencyInstance(localeBR);
        String retorno = formate.format(dValor);
        return (dValor < 0 ? "-" : "") + retorno.substring(retorno.indexOf('$') + 2); //Retira o R$
    }
}
