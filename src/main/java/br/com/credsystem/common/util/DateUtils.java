package br.com.credsystem.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
    public static Locale localeBR = new Locale("pt", "BR");


    public static int iDiffDays(Date d0, Date d1) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d0);
        cal2.setTime(d1);
        return iDiffDays(cal1.getTime(), cal2.getTime());
    }

    public static int iDiffDays(java.util.Date d0, java.util.Date d1) {
        return iDiffDays(convSQLDate(d0), convSQLDate(d1));
    }

    public static java.sql.Date convSQLDate(java.util.Date data) {
        return new java.sql.Date(data.getTime());
    }

    public static Calendar somaDias(Calendar cal, int nDias) {
        Calendar gCal = (Calendar) cal.clone();
        gCal.add(Calendar.DAY_OF_MONTH, nDias);
        return gCal;
    }

    public static Calendar somaDias(java.util.Date data, int nDias) {
        Calendar gCal = new GregorianCalendar();
        gCal.setTime(data);
        gCal.add(Calendar.DAY_OF_MONTH, nDias);
        return gCal;
    }

    public static String formataData(java.util.Date dt, String sFormato) {
        if (dt == null) {
            return "";
        }
        String sData = "";
        try {
            SimpleDateFormat frm = new SimpleDateFormat(sFormato, localeBR);
            sData = frm.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sData;
    }
}
