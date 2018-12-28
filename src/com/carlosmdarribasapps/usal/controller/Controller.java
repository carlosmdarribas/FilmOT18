package com.carlosmdarribasapps.usal.controller;

import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;
import com.carlosmdarribasapps.usal.model.FilmLibrary;
import com.carlosmdarribasapps.usal.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final CMFileHandler fileHandler = new CMFileHandler();
    private FilmLibrary filmLibrary = new FilmLibrary();

    /**
     * Realizamos las comprobaciones y las secuencias de arranque.
     */
    public void arranque() throws IOException {
        List<String> readedFilms;

        // Comprobamos si existe el binario "peliculas.bin" en la carpeta.
        if (!CMFileHandler.checkIfFileExists(Constants.FILMS_BIN_PATH)) {
            // El archivo existe, lo leemos de BIN.

        } else {
            // El archivo no existe, leemos e importamos el TXT.

            try {
                // Esto devuelve un array de String, que son cada una de las lineas.
                List<String> linesArray = fileHandler.readDataFromTextFile(Constants.FILMS_TXT_PATH);
                for (String line : linesArray) {
                    String[] currentLine = line.split("#");
                    Film localFilm = new Film();

                    String[] directionString = currentLine[4].split("\t");
                    List<String> directorArray = new ArrayList<String>();
                    for (String directorName : directionString) {
                        directorArray.add(directorName);
                    }

                    String[] cast = currentLine[8].split("\t");

                    List<String> castArray = new ArrayList<>();
                    for (String actorName : cast) {
                        castArray.add(actorName);
                    }

                    localFilm.setName(currentLine[0]);
                    localFilm.setYear(Integer.parseInt(currentLine[1]));
                    localFilm.setDuration(Integer.parseInt(currentLine[2]));
                    localFilm.setCountry(currentLine[3]);
                    localFilm.setDirection(directorArray);
                    localFilm.setGuion(currentLine[5]);
                    localFilm.setMusic(currentLine[6]);
                    localFilm.setPhotography(currentLine[7]);
                    localFilm.setCast(castArray);
                    localFilm.setProducer(currentLine[9]);
                    localFilm.setGender(currentLine[10]);
                    localFilm.setSynopsis(currentLine[11]);

                    filmLibrary.addFilm(localFilm);
                }
            } catch (IOException exception) {
                System.err.println("El fichero no existe. Creelo en " + Constants.FILMS_TXT_PATH + " y vuelva a intentarlo.");
                System.exit(0);
            }

        }

    }

    public void salidaPrograma() throws IOException {
        fileHandler.filmsToBin(Constants.FILMS_BIN_PATH, filmLibrary.getFilms());
        fileHandler.actorsToBin(Constants.ACTORS_BIN_PATH, filmLibrary.getActors());
        fileHandler.directorsToBin(Constants.DIRECTORS_BIN_PATH, filmLibrary.getDirectors());
    }

    public boolean checkIfDirectorExists(String directorName) {
        if (filmLibrary.getDirectors() == null || filmLibrary.getDirectors().isEmpty()) return false;

        for (Director director : filmLibrary.getDirectors())
            if (director.getName().equals(directorName)) return true;

        return false;
    }

    public boolean checkIfActorExists(String actorName) {
        if (filmLibrary.getActors() == null || filmLibrary.getActors().isEmpty()) return false;

        for (Actor actors : filmLibrary.getActors())
            if (actors.getName().equals(actorName)) return true;

        return false;
    }

    public void addFilmToCollection(Film film) {
        filmLibrary.addFilm(film);
    }

    public void addDirectorToCollection(Director director) {
        filmLibrary.addDirector(director);
    }

    public void addActorToCollection(Actor actor) {
        filmLibrary.addActor(actor);
    }


    public List<Film> getFilms() {
        return filmLibrary.getFilms();
    }

    public void removeFilm(Film film) {
        filmLibrary.removeFilm(film);
    }




    /*
    Parte de EXPORTAR
     */

    public List<String> exportDirectorsToColumns() throws IOException{
        List<String>productos = filmLibrary.getDirectorsInColumns();

        if( null == productos){
            productos = new ArrayList<>();
            productos.add("No data aviable");
        }

        //fh.exportToTextFile(Constantes.RUTA_PRODUCTOS_COL, productos);
        return null;
    }



    public void exportFilmsToHTML() throws IOException{
        List<String>productos = filmLibrary.getFilmsInHTML();
        if( null == productos){
            productos = new ArrayList<>();
            productos.add("<TR><TD><STRONG>No data aviable</STRONG></TD></TR>");
        }
        productos.add(0,String.format("<TR> <TD><STRONG>%s</STRONG></TD> <TD><STRONG>%s</STRONG></TD> <TD><STRONG>%s</STRONG></TD> <TD><STRONG>%s</STRONG></TD></TR>","Nombre","Precio","Iva","Stock"));

        /*
        fh.generarEnvoltorioHtml(productos,"PRODUCTOS");
        fh.exportToTextFile(Constantes.RUTA_PRODUCTOS_HTML,productos);
        */
    }
}
