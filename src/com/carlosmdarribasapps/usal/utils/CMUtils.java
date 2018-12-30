package com.carlosmdarribasapps.usal.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CMUtils {
    public static Date stringToDate(String dateString, String format) {

        try {
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

    public static int askForInteger(String message, Scanner scanner) {
        boolean exit;

        do {
            System.out.print(message);
            String input = scanner.nextLine();
            if (isStringParsableToInt(input)) {
                return Integer.parseInt(input);
            } else {
                System.err.println("Error. Solo se deben introducir enteros.");
                exit = false;
            }

        } while (!exit);

        return 0;
    }
}
