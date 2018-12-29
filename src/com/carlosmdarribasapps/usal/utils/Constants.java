package com.carlosmdarribasapps.usal.utils;

import java.io.File;

public class Constants {

    public final static String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop";
    public final static String MOVIES_PATH = DESKTOP_PATH + File.separator + "Filmot18";

    // Ficheros BIN y TXT
    public final static String FILMS_BIN_PATH = MOVIES_PATH + File.separator + "peliculas.bin";
    public final static String FILMS_TXT_PATH = MOVIES_PATH + File.separator + "peliculas.txt";

    public final static String ACTORS_BIN_PATH = MOVIES_PATH + File.separator + "actores.bin";
    public final static String ACTORS_TXT_PATH = MOVIES_PATH + File.separator + "actores.txt";

    public final static String DIRECTORS_BIN_PATH = MOVIES_PATH + File.separator + "directores.bin";
    public final static String DIRECTORS_TXT_PATH = MOVIES_PATH + File.separator + "directores.txt";

    public final static String COLUMNED_DIRECTOR_FORMAT  = "%-30s%10.2f%10.2f%10d";
    public final static String FILM_TABLE_FORMAT  = "|%-30s|%6s|%4s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s";
    public final static String FILM_ACTOR_TABLE_FORMAT  = "|%-50s|%6s|%6s|%25s|%20s|";
    public final static String DIRECTOR_TABLE_FORMAT  = "|%-50s|%15s|%20s|%25s|%30s|";

}
