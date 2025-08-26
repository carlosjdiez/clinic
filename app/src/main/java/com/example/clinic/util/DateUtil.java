package com.example.clinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static Date parse(String date) throws ParseException {
        return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(date);
    }

    public static String format(Date date) {
        return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date);
    }
}
