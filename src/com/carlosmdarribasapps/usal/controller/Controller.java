package com.carlosmdarribasapps.usal.controller;

import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Comparators.FilmAlphabeticComparator;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;
import com.carlosmdarribasapps.usal.model.FilmLibrary;
import com.carlosmdarribasapps.usal.utils.CMUtils;
import com.carlosmdarribasapps.usal.utils.Constants;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        if (CMFileHandler.checkIfFileExists(Constants.FILMS_BIN_PATH)) {
            // El archivo existe, lo leemos de BIN.
            System.out.println("[DEBUG] Existe el BIN de peliculas. Leemos de él.");

            try {
                filmLibrary.setFilms(fileHandler.binToFilms(Constants.FILMS_BIN_PATH));
            } catch (ClassNotFoundException exception) {
                System.err.println("Error al importar de binario.");
            }

        } else if (CMFileHandler.checkIfFileExists(Constants.FILMS_TXT_PATH)) {
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

                        Actor newActor = new Actor();
                        newActor.setName(actorName);
                        newActor.setFilms(new ArrayList<String>(Arrays.asList(currentLine[0])));
                        this.addEmptyActorToCollection(newActor);
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
                    localFilm.setgenre(currentLine[10]);
                    localFilm.setSynopsis(currentLine[11]);

                    filmLibrary.addFilm(localFilm);
                }
            } catch (IOException exception) {
                System.err.println("El fichero no existe. Creelo en " + Constants.FILMS_TXT_PATH + " y vuelva a intentarlo.");
                System.exit(0);
            }

        }

        /**
         * Directores
         */
        if (CMFileHandler.checkIfFileExists(Constants.DIRECTORS_BIN_PATH)) {
            // El archivo existe, lo leemos de BIN.
            System.out.println("[DEBUG] Existe el BIN de directores. Leemos de él.");

            try {
                filmLibrary.setDirectors(fileHandler.binToDirectors(Constants.DIRECTORS_BIN_PATH));
            } catch (ClassNotFoundException exception) {
                System.err.println("Error al importar de binario.");
            }

        } else if (CMFileHandler.checkIfFileExists(Constants.DIRECTORS_TXT_PATH)) {
            System.out.println("[DEBUG] Leemos del TXT al no existir el de binario.");

            try {
                // Esto devuelve un array de String, que son cada una de las lineas.
                List<String> linesArray = fileHandler.readDataFromTextFile(Constants.DIRECTORS_TXT_PATH);
                for (String line : linesArray) {
                    String[] currentLine = line.split("#");

                    Director director = new Director();
                    director.setName(currentLine[0]);
                    director.setBirthdate(CMUtils.stringToDate(currentLine[1], "yyyy-MM-dd"));
                    director.setNationality(currentLine[2]);
                    director.setJob(currentLine[3]);

                    String[] allFilms = currentLine[4].split("\t");
                    List<String> filmsArray = new ArrayList<String>();
                    for (String filmName : allFilms) {
                        filmsArray.add(filmName);
                    }
                    director.setFilms(filmsArray);

                    this.addEmptyDirectorToCollection(director);
                }
            } catch (ArrayIndexOutOfBoundsException indexOut) {
                System.err.println("ERROR. Elementos insuficientes.");
            } catch (IllegalArgumentException e) {
                System.err.println("ERROR. Formato de elementos ilegal.");
            } catch (IOException exception) {
                System.err.println("ERROR importando fichero de texto.");
            }
        }

        /**
         * Actores
         */
        if (CMFileHandler.checkIfFileExists(Constants.ACTORS_BIN_PATH)) {
            // El archivo existe, lo leemos de BIN.
            System.out.println("[DEBUG] Existe el BIN de actores. Leemos de él.");

            try {
                filmLibrary.setActors(fileHandler.binToActors(Constants.ACTORS_BIN_PATH));
            } catch (ClassNotFoundException exception) {
                System.err.println("Error al importar de binario.");
            }

        } else if (CMFileHandler.checkIfFileExists(Constants.ACTORS_TXT_PATH)) {
            try {
                // Esto devuelve un array de String, que son cada una de las lineas.
                List<String> linesArray = fileHandler.readDataFromTextFile(Constants.ACTORS_TXT_PATH);
                for (String line : linesArray) {
                    String[] currentLine = line.split("#");

                    Actor actor = new Actor();
                    actor.setName(currentLine[0]);
                    actor.setBirthdate(CMUtils.stringToDate(currentLine[1], "yyyy-MM-dd"));
                    actor.setNationality(currentLine[2]);
                    actor.setDebutYear((CMUtils.isStringParsableToInt(currentLine[3])) ? Integer.parseInt(currentLine[3]) : -1);


                    String[] allFilms = currentLine[4].split("\t");
                    List<String> filmsArray = new ArrayList<String>();
                    for (String filmName : allFilms) {
                        filmsArray.add(filmName);
                    }
                    actor.setFilms(filmsArray);

                    this.addEmptyActorToCollection(actor);
                }
            } catch (ArrayIndexOutOfBoundsException indexOut) {
                System.err.println("ERROR. Elementos insuficientes.");
            } catch (IOException e) {
                System.err.println("Elemento inválido detectado");
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

    public Director getDirectorFromCollectionWithName(String directorName) {
        for (Director director : this.getDirectors()) if (director.getName().equals(directorName)) return director;

        return null;
    }

    public Actor getActorFromCollectionWithName(String actorName) {
        for (Actor actor : this.getActors()) if (actor.getName().equals(actorName)) return actor;

        return null;
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

    public void addEmptyDirectorToCollection(Director director) {
        filmLibrary.addDirector(director);
    }

    public void addEmptyActorToCollection(Actor actor) {
        filmLibrary.addActor(actor);
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

    public List<Director> getDirectors() {
        return filmLibrary.getDirectors();
    }

    public void removeDirector(Director director) {
        filmLibrary.removeDirector(director);
    }

    public List<Actor> getActors() {
        return filmLibrary.getActors();
    }

    public void removeActor(Actor actor) {
        filmLibrary.removeActor(actor);
    }

    public Film getFilmByName(String name) {
        for (Film film : this.getFilms()) {
            if (film.getName().equals(name)) return film;
        }

        return null;
    }

    public List<Film> getSortedFilmsAlph() {
        List<Film> sortedFilms = new ArrayList<>(this.getFilms());

        Collections.sort(sortedFilms, new FilmAlphabeticComparator());

        return sortedFilms;
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
