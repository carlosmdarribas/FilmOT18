//
//  Constants.java
//  FilmOT18
//
//  Created by Carlos Martin de Arribas on 27/12/18
//

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

    /* Exportar */
    public final static String DIRECTORS_COL_PATH = MOVIES_PATH + File.separator + "directores.col";
    public final static String FILMS_HTML_PATH = MOVIES_PATH + File.separator + "peliculas.html";

    /* Formatos */
    public final static String FILM_TABLE_FORMAT  = "|%-30s|%6s|%10s|%20s|%30s|%40s|%20s|%20s|%100s|%20s|%10s|%-130s";
    public final static String FILM_ACTOR_TABLE_FORMAT  = "|%-50s|%-6s|%-6s|%-25s|%-20s|";
    public final static String DIRECTOR_TABLE_FORMAT  = "|%-25s|%-15s|%-25s|%-70s|%-80s|";
    public final static String ACTOR_TABLE_FORMAT  = "|%-40s|%-25s|%-25s|%-20s|%-80s|";

    public final static String HTML_TABLE_HEADER = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Tabla de películas</title>\n" +
            "\t<style type=\"text/css\">\n" +
            "\t\ttable.blueTable {\n" +
            "\t\t  border: 1px solid #1C6EA4;\n" +
    public final static String HTML_ROW_FORMAT = "<TR><TD>%s</TD><TD>%d</TD><TD>%d</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD></TR>";