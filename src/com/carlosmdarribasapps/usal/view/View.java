package com.carlosmdarribasapps.usal.view;

import com.carlosmdarribasapps.usal.controller.Controller;
import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;
import com.carlosmdarribasapps.usal.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void runMenu(String[] menus) {
        boolean salir = false;
        String respuesta = null;

        try {
            controller.arranque();
        } catch (IOException e) {
            System.err.println("ERROR: los datos no pudieron ser importados\nAbortando ejecucion.");
            salir = true;
        }

        while (!salir) {
            System.out.print(menus[0]);
            do {
                respuesta = scanner.nextLine();
            } while(respuesta.isEmpty());

            switch(respuesta){
                case "1": filesMenu(menus[1]);  break;
                case "2": filmsMenu(menus[2]);  break;
                case "3": directorMenu(menus[3]);break;
                case "4": actorsMenu(menus[4]);  break;
                case "5": listMenu(menus[5]);    break;
                case "S": case "s": salir = true;  break;

                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        }

        try {
            controller.salidaPrograma();
        } catch(IOException e) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }

    /**
        Relacionado con opción "ARCHIVOS"
     */
    public void filesMenu(String menu) {
        boolean salir = false;
        String respuesta = null;

        do {
            System.out.print(menu);
            do {  respuesta = scanner.nextLine();   } while (respuesta.isEmpty());

            /* 1) Exportar directores a encolumnado || 2) Exportar peliculas a documento HTML */
            switch (respuesta){

                case "1": exportDirectorsToColumns(); break;
                case "2": exportFilmsToHTML();        break;
                case "V": case "v": salir = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!salir);
    }

    public void exportDirectorsToColumns() {
        try {
            controller.exportDirectorsToColumns();
        } catch (IOException ex) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }

    public void exportFilmsToHTML() {
        try {
            controller.exportFilmsToHTML();
        } catch (IOException ex) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }


    /**
        Relacionada con la opción "PELICULAS"
     */
    public void filmsMenu(String menu) {
        boolean salir = false;
        String respuesta = null;

        do {
            System.out.print(menu);
            do {  respuesta = scanner.nextLine();   } while (respuesta.isEmpty());

            /* 1) .append("\n\t1) Nueva película")
                .append("\n\t2) Eliminar película")
                .append("\n\t3) Modificar película")
                .append("\n\t3) Mostrar información de película") */
            switch(respuesta){

                case "1": newFilm(); break;
                case "2": removeFilm();        break;
                case "3": modifyFilm();        break;
                case "4": showFilmInformation();        break;
                case "V": case "v": salir = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!salir);
    }

    public void newFilm() {
        Film newFilm = new Film();

        System.out.println("\nSe va a proceder a crear una película nueva.\n");

        // Nombre
        System.out.print("\tNombre de la película: ");
        newFilm.setName(scanner.nextLine());

        // Año
        System.out.print("\tAño de creación de la película: ");
        newFilm.setYear(scanner.nextInt());

        // Duracion
        System.out.print("\tDuración de la película: ");
        newFilm.setDuration(scanner.nextInt());

        scanner.nextLine(); // Cogemos el salto de linea.

        // País de origen
        System.out.print("\tPaís de la película: ");
        newFilm.setCountry(scanner.nextLine());

        // Dirección de la película.
        List<String> directors = new ArrayList<>();
        boolean exit = false;
        do {
            System.out.print("\tDirector de la película: ");
            String directorName = scanner.nextLine();
            directors.add(directorName);


            if (!controller.checkIfDirectorExists(directorName)) {
                // El director no existe. Se crea vacio.
                controller.addDirectorToCollection(new Director(directorName));
            }

            System.out.print("¿Desea añadir más directores? (S/n)");
            String exitAsk = scanner.nextLine();

            if (!"sS".contains(exitAsk)) exit = true;
        } while (!exit);
        newFilm.setDirection(directors);

        // Guionistas
        System.out.print("\tGuionista/s: ");
        newFilm.setGuion(scanner.nextLine());

        // Música
        System.out.print("\tMusica: ");
        newFilm.setMusic(scanner.nextLine());

        // Fotografía
        System.out.print("\tFotografía: ");
        newFilm.setPhotography(scanner.nextLine());


        // Casting de actores
        List<String> cast = new ArrayList<>();
        exit = false;
        do {
            System.out.print("\tNombre del actor / actriz: ");
            String actorName = scanner.nextLine();
            cast.add(actorName);


            if (!controller.checkIfActorExists(actorName)) {
                // El director no existe. Se crea vacio.
                controller.addActorToCollection(new Actor(actorName));
            }

            System.out.print("¿Desea añadir más actores? (S/n)");
            String exitAsk = scanner.nextLine();

            if (!"sS".contains(exitAsk)) exit = true;
        } while (!exit);
        newFilm.setCast(cast);

        // Producción
        System.out.print("\tProductor/es: ");
        newFilm.setProducer(scanner.nextLine());

        // Género
        System.out.print("\tGénero de la película: /s: ");
        newFilm.setGender(scanner.nextLine());

        // Sinopsis
        System.out.print("\tSinopsis: ");
        newFilm.setSynopsis(scanner.nextLine());

        controller.addFilmToCollection(newFilm);
    }

    public void removeFilm() {
        int i = 1;
        List<Film> films = controller.getFilms();
        if (films.isEmpty()) { System.err.println("No hay películas para eliminar."); return; }

        System.out.println("Listado de películas: ");
        for (Film film : films) {
            System.out.println("\t["+(i++)+"] " + film.getName());
        }

        System.out.print("Introduzca el número de película (entre corchetes) a borrar: ");
        Integer deleteIndex = scanner.nextInt();


        if (deleteIndex < 0 || deleteIndex > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            controller.removeFilm(films.get(deleteIndex-1));
        }
    }

    public void modifyFilm() {

    }

    public void showFilmInformation() {
        System.out.print("¿Desea seleccionar la película por lista o nombre? L/n");
        String exitAsk = scanner.nextLine();

        Film selectedFilm = null;

        if ("Ll".contains(exitAsk)) {
            // Mostramos la lista.

            int i = 1;
            List<Film> films = controller.getFilms();
            if (films.isEmpty()) { System.err.println("No hay películas para eliminar."); return; }

            System.out.println("Listado de películas: ");
            for (Film film : films) {
                System.out.println("\t["+(i++)+"] " + film.getName());
            }

            System.out.print("Introduzca el número de la película que desea mostrar: ");
            Integer filmIndex = scanner.nextInt();

            selectedFilm = films.get(filmIndex-1);
        } else {
            System.out.print("Nombre de la película: ");
            String filmName = scanner.nextLine();

            for (Film film : controller.getFilms()) {
                if (film.getName().contains(filmName)) {
                    selectedFilm = film;
                    break;
                }
            }

            if (selectedFilm == null) {
                System.err.println("No hay películas con ese nombre.");
                return;
            }
        }

        System.out.printf(Constants.FILM_TABLE_FORMAT, selectedFilm.getName(), selectedFilm.getYear(), selectedFilm.getDuration(), selectedFilm.getCountry(),
                selectedFilm.getDirection().toString(), selectedFilm.getGuion(), selectedFilm.getMusic(), selectedFilm.getPhotography(), selectedFilm.getCast().toString(),
                selectedFilm.getProducer(), selectedFilm.getGender(), selectedFilm.getSynopsis());
    }

    /**
     Relacionada con la opción "DIRECTORES"
     */
    public void directorMenu(String menu) {
        boolean salir = false;
        String respuesta = null;

        do {
            System.out.print(menu);
            do {  respuesta = scanner.nextLine();   } while (respuesta.isEmpty());

            /*  1) Nuevo director
                2) Eliminar director
                3) Modificar director */
            switch(respuesta){

                case "1": newDirector(); break;
                case "2": removeDirector();        break;
                case "3": modifyDirector();        break;
                case "V": case "v": salir = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!salir);
    }

    public void newDirector() {
        Director director = new Director();
    }

    public void removeDirector() {

    }

    public void modifyDirector() {

    }

    /**
     Relacionada con la opción "ACTORES"
     */
    public void actorsMenu(String menu) {
        boolean salir = false;
        String respuesta = null;

        do {
            System.out.print(menu);
            do {  respuesta = scanner.nextLine();   } while (respuesta.isEmpty());

            /*  .append("\n\t1) Nuevo actor")
                .append("\n\t2) Eliminar actor")
                .append("\n\t3) Modificar actor")
                .append("\n\t4) Listar películas de un actor") */
            switch(respuesta){

                case "1": newActor();       break;
                case "2": removeActor();    break;
                case "3": modifyActor();    break;
                case "4": listActorMovies();    break;
                case "V": case "v": salir = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!salir);
    }

    public void newActor() {

    }

    public void removeActor() {

    }

    public void modifyActor() {

    }

    public void listActorMovies() {

    }


    /**
     Relacionada con la opción "LISTADO"
     */
    public void listMenu(String menu) {
        boolean salir = false;
        String respuesta = null;

        do {
            System.out.print(menu);
            do {  respuesta = scanner.nextLine();   } while (respuesta.isEmpty());

            /*  1) Mostrar películas alfabeticamente
                2) Mostrar directores por nacionalidad y edad
                3) Mostrar actores por debut y nombre */
            switch(respuesta){

                case "1": listFilmsAlph(); break;
                case "2": listDirectorsNac_Age();        break;
                case "3": listActorsName_Debut();        break;
                case "V": case "v": salir = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!salir);
    }

    public void listFilmsAlph() {

    }

    public void listDirectorsNac_Age() {

    }

    public void listActorsName_Debut() {
        
    }



    // Funciones añadidas

}
