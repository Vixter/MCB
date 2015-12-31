package ru.winfected.mcb.utils;

import java.text.SimpleDateFormat;

/**
 * Created by winfe on 31.12.2015.
 */
public class Date {

    public static String DateToUTCString(java.util.Date date, String dataformat) {
        final SimpleDateFormat fmt = new SimpleDateFormat(dataformat);
        return fmt.format(date);
    }

}
