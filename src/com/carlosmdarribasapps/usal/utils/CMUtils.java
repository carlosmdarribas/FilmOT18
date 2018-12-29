package com.carlosmdarribasapps.usal.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CMUtils {
    public static Date stringToDate(String dateString, String format) {

        try {
            System.out.println("Formato de fecha: "+format+" con respecto al elemento "+dateString);
            DateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(dateString);
        } catch (ParseException exception) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isStringParsableToInt(String input){
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
