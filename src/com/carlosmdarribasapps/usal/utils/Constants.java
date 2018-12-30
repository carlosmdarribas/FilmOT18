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
            "\t\t  background-color: #EEEEEE;\n" +
            "\t\t  width: 100%;\n" +
            "\t\t  text-align: left;\n" +
            "\t\t  border-collapse: collapse;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable td, table.blueTable th {\n" +
            "\t\t  border: 1px solid #AAAAAA;\n" +
            "\t\t  padding: 3px 2px;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable tbody td {\n" +
            "\t\t  font-size: 13px;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable tr:nth-child(even) {\n" +
            "\t\t  background: #D0E4F5;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable thead {\n" +
            "\t\t  background: #1C6EA4;\n" +
            "\t\t  background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\n" +
            "\t\t  background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\n" +
            "\t\t  background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1C6EA4 100%);\n" +
            "\t\t  border-bottom: 2px solid #444444;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable thead th {\n" +
            "\t\t  font-size: 15px;\n" +
            "\t\t  font-weight: bold;\n" +
            "\t\t  color: #FFFFFF;\n" +
            "\t\t  text-align: center;\n" +
            "\t\t  border-left: 2px solid #D0E4F5;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable thead th:first-child {\n" +
            "\t\t  border-left: none;\n" +
            "\t\t}\n" +
            "\n" +
            "\t\ttable.blueTable tfoot td {\n" +
            "\t\t  font-size: 14px;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable tfoot .links {\n" +
            "\t\t  text-align: right;\n" +
            "\t\t}\n" +
            "\t\ttable.blueTable tfoot .links a{\n" +
            "\t\t  display: inline-block;\n" +
            "\t\t  background: #1C6EA4;\n" +
            "\t\t  color: #FFFFFF;\n" +
            "\t\t  padding: 2px 8px;\n" +
            "\t\t  border-radius: 5px;\n" +
            "\t\t}\n" +
            "\t</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "\t\n" +
            "\n" +
            "<table class=\"blueTable\">\n" +
            "<thead>\n" +
            "<tr>\n" +
            "<th>Nombre</th>\t\n" +
            "<th>Año</th>\n" +
            "<th>Duración</th>\n" +
            "<th>País</th>\n" +
            "<th>Dirección</th>\n" +
            "<th>Guión</th>\n" +
            "<th>Música</th>\n" +
            "<th>Fotografía</th>\n" +
            "<th>Reparto</th>\n" +
            "<th>Productor</th>\n" +
            "<th>Género</th>\n" +
            "<th>Sinopsis</th></th>\n" +
            "</tr>\n" +
            "</thead>\n" +
            "<tbody>";

    public static final String HTML_TABLE_FOOTER = "</table><br><center><i>Developed by Carlos Martín de Arribas in Salamanca for USAL.</i></center></body></html>";
}
