package br.com.credsystem.common.util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtils {

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
}
