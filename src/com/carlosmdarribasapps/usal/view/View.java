package com.carlosmdarribasapps.usal.view;

import com.carlosmdarribasapps.usal.controller.Controller;
import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;
import com.carlosmdarribasapps.usal.utils.CMUtils;
import com.carlosmdarribasapps.usal.utils.Constants;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class View {
    private final Controller controller = new Controller();
    private final Scanner scanner = new Scanner(System.in);

    public void runMenu(String[] menus) {
        boolean exit = false;
        String response;

        try {
            controller.arranque();
        } catch (IOException e) {
            System.err.println("ERROR: los datos no pudieron ser importados\nAbortando ejecucion.");
            exit = true;
        }

        while (!exit) {
            System.out.print(menus[0]);
            do {
                response = scanner.nextLine();
            } while(response.isEmpty());

            switch(response){
                case "1": filesMenu(menus[1]);  break;
                case "2": filmsMenu(menus[2]);  break;
                case "3": directorMenu(menus[3]);break;
                case "4": actorsMenu(menus[4]);  break;
                case "5": listMenu(menus[5]);    break;
                case "S": case "s": exit = true;  break;

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
    private void filesMenu(String menu) {
        boolean exit = false;
        String response;

        do {
            System.out.print(menu);
            do {  response = scanner.nextLine();   } while (response.isEmpty());

            /* 1) Exportar directores a encolumnado || 2) Exportar peliculas a documento HTML */
            switch (response){

                case "1": exportDirectorsToColumns(); break;
                case "2": exportFilmsToHTML();        break;
                case "V": case "v": exit = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!exit);
    }

    private void exportDirectorsToColumns() {
        try {
            controller.exportDirectorsToColumns();
        } catch (IOException ex) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }

    private void exportFilmsToHTML() {
        try {
            controller.exportFilmsToHTML();
        } catch (IOException ex) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }


    /**
        Relacionada con la opción "PELICULAS"
     */
    private void filmsMenu(String menu) {
        boolean exit = false;
        String response;

        do {
            System.out.print(menu);
            do {  response = scanner.nextLine();   } while (response.isEmpty());

            /* 1) .append("\n\t1) Nueva película")
                .append("\n\t2) Eliminar película")
                .append("\n\t3) Modificar película")
                .append("\n\t3) Mostrar información de película") */
            switch(response){

                case "1": newFilm(); break;
                case "2": removeFilm();        break;
                case "3": modifyFilm();        break;
                case "4": showFilmInformation();        break;
                case "V": case "v": exit = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!exit);
    }

    private void newFilm() {
        Film newFilm = new Film();

        System.out.println("\nSe va a proceder a crear una película nueva.\n");

        // Nombre
        System.out.print("\tNombre de la película: ");
        newFilm.setName(scanner.nextLine());

        // Año
        newFilm.setYear(CMUtils.askForInteger("\tAño de creación de la película: ", scanner));

        // Duracion
        newFilm.setDuration(CMUtils.askForInteger("\tDuración de la película: ", scanner));

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

            Director director = controller.getDirectorFromCollectionWithName(directorName);
            if (director == null) {
                // El director no existe. Se crea vacio.

                Director newDirector = new Director();
                newDirector.setName(directorName);
                newDirector.setFilms(new ArrayList<>(Collections.singletonList(newFilm.getName())));
                controller.addEmptyDirectorToCollection(newDirector);
            } else {
                // El director existe. Por lo que le achacamos la película.
                director.getFilms().add(newFilm.getName());
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
            System.out.print("\tNombre del actor/actriz: ");
            String actorName = scanner.nextLine();
            cast.add(actorName);

            Actor actor = controller.getActorFromCollectionWithName(actorName);
            if (actor == null) {
                // El director no existe. Se crea vacio.

                Actor newActor = new Actor();
                newActor.setName(actorName);
                newActor.setFilms(new ArrayList<>(Collections.singletonList(newFilm.getName())));
                controller.addEmptyActorToCollection(newActor);
            } else {
                // El director existe. Por lo que le achacamos la película.
                actor.getFilms().add(newFilm.getName());
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
        newFilm.setGenre(scanner.nextLine());

        // Sinopsis
        System.out.print("\tSinopsis: ");
        newFilm.setSynopsis(scanner.nextLine());

        controller.addFilmToCollection(newFilm);
    }

    private void removeFilm() {
        int i = 1;
        List<Film> films = controller.getFilms();
        if (films == null || films.isEmpty()) { System.err.println("No hay películas para eliminar."); return; }

        System.out.println("Listado de películas: ");
        for (Film film : films) {
            System.out.println("\t["+(i++)+"] " + film.getName());
        }

        int deleteIndex = CMUtils.askForInteger("Introduzca el número de película (entre corchetes) a borrar: ", scanner);
        if (deleteIndex <= 0 || deleteIndex > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
                controller.removeFilm(films.get(deleteIndex-1));
            } catch (IndexOutOfBoundsException exp) {
                System.err.println("ERROR. Índice incorrecto.");
            }
        }
    }

    private void modifyFilm() {
        int i = 1;
        List<Film> films = controller.getFilms();
        if (films == null || films.isEmpty()) { System.err.println("No hay películas dadas de alta."); return; }

        System.out.println("Listado de películas: ");
        for (Film film : films) {
            System.out.println("\t["+(i++)+"] " + film.getName());
        }

        int index = CMUtils.askForInteger("Introduzca el número (entre corchetes) que corresponde a la película a modificar: ", scanner);
        if (index <= 0 || index > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
                Film selectedFilm = films.get(index - 1);

                System.out.print("Año de la película (actual " + selectedFilm.getYear() + " (Intro para valor actual)): ");
                String change = scanner.nextLine();
                if (!change.equals("") && CMUtils.isStringParsableToInt(change))
                    selectedFilm.setYear(Integer.parseInt(change));
                if (!CMUtils.isStringParsableToInt(change))
                    System.err.println("ERROR. Elemento introducido inválido. Se mantendrá el original.");

                System.out.print("Duración de la película (actual " + selectedFilm.getDuration() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("") && CMUtils.isStringParsableToInt(change))
                    selectedFilm.setDuration(Integer.parseInt(change));
                if (!CMUtils.isStringParsableToInt(change))
                    System.err.println("ERROR. Elemento introducido inválido. Se mantendrá el original.");

                System.out.print("País de la película (actual " + selectedFilm.getCountry() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setCountry(change);

                System.out.print("Guionistas de la película (actual " + selectedFilm.getGuion() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setGuion(change);

                System.out.print("Músicos de la película (actual " + selectedFilm.getMusic() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setMusic(change);

                System.out.print("Fotografía de la película (actual " + selectedFilm.getPhotography() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setPhotography(change);

                System.out.print("Productor de la película (actual " + selectedFilm.getProducer() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setProducer(change);

                System.out.print("Género de la película (actual " + selectedFilm.getGenre() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setGenre(change);

                System.out.print("Sinopsis de la película (actual " + selectedFilm.getSynopsis() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedFilm.setSynopsis(change);

            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Selección inválida.");
            }
        }
    }

    private void showFilmInformation() {
        List<Film> films = controller.getFilms();
        if (films == null || films.isEmpty()) { System.err.println("No hay películas a mostrar."); return; }

        System.out.print("¿Desea seleccionar la película por lista o nombre? L/N");
        String exitAsk = scanner.nextLine();

        Film selectedFilm = null;

        if ("Ll".contains(exitAsk)) {
            // Mostramos la lista.

            int i = 1;

            System.out.println("Listado de películas: ");
            for (Film film : films) {
                System.out.println("\t["+(i++)+"] " + film.getName());
            }

            int filmIndex = CMUtils.askForInteger("Introduzca el número de la película que desea mostrar: ", scanner);
            if (filmIndex <= 0 || filmIndex > i) {
                System.err.println("ERROR. Índice incorrecto.");
                return;
            } else {
                try {
                    selectedFilm = films.get(filmIndex - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("ERROR. Indice incorrecto");
                    return;
                }
            }
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

        System.out.printf(Constants.FILM_TABLE_FORMAT+"\n", "NOMBRE", "AÑO", "DURACIÓN", "PAÍS", "DIRECCIÓN", "GUIÓN", "MÚSICA", "FOTOGRAFÍA", "REPARTO", "PRODUCTORA", "GÉNERO", "SINOPSIS");
        System.out.printf(Constants.FILM_TABLE_FORMAT, selectedFilm.getName(), selectedFilm.getYear(), selectedFilm.getDuration(), selectedFilm.getCountry(),
                String.join(", ", selectedFilm.getDirection()), selectedFilm.getGuion(), selectedFilm.getMusic(), selectedFilm.getPhotography(),
                String.join(", ", selectedFilm.getCast()), selectedFilm.getProducer(), selectedFilm.getGenre(), selectedFilm.getSynopsis());
    }

    /**
     Relacionada con la opción "DIRECTORES"
     */
    private void directorMenu(String menu) {
        boolean exit = false;
        String response;

        do {
            System.out.print(menu);
            do {  response = scanner.nextLine();   } while (response.isEmpty());

            /*  1) Nuevo director
                2) Eliminar director
                3) Modificar director */
            switch(response){

                case "1": newDirector(); break;
                case "2": removeDirector();        break;
                case "3": modifyDirector();        break;
                case "V": case "v": exit = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!exit);
    }

    private void newDirector() {
        /*
            private String name;
            private Date birthdate;
            private String nationality;
            private String job;
            private List<String> films;
         */

        System.out.println("Introduzca los datos del director: ");

        Director director = new Director();

        // Nombre
        System.out.print("Nombre del director: ");
        director.setName(scanner.nextLine());

        // Fecha de nacimiento
        boolean exit;
        do {
            System.out.print("Fecha de nacimiento del director, en formato dd/MM/yyyy: ");

            Date birthdate = CMUtils.stringToDate(scanner.nextLine(), "dd-MM-yyyy");
            if (birthdate == null) exit = false;
            else {
                director.setBirthdate(birthdate);
                exit = true;
            }

        } while (!exit);

        // Nacionalidad
        System.out.print("Nacionalidad del director: ");
        director.setNationality(scanner.nextLine());

        // Ocupación
        System.out.print("Ocupación del director: ");
        director.setJob(scanner.nextLine());

        // Películas del director.
        List<String> films = new ArrayList<>();
        exit = false;
        do {
            System.out.print("\tNombre de la película: ");
            films.add(scanner.nextLine());

            System.out.print("¿Desea añadir más películas? (S/n)");
            String exitAsk = scanner.nextLine();

            if (!"sS".contains(exitAsk)) exit = true;
        } while (!exit);
        director.setFilms(films);

        // Guardamos en la colección.
        controller.addEmptyDirectorToCollection(director);
    }

    private void removeDirector() {
        int i = 1;
        List<Director> directors = controller.getDirectors();
        if (directors == null || directors.isEmpty()) { System.err.println("No hay directores dados de alta para eliminar."); return; }

        System.out.println("Listado de directores: ");
        for (Director director : directors) {
            System.out.println("\t["+(i++)+"] " + director.getName());
        }

        int deleteIndex = CMUtils.askForInteger("Introduzca el número (entre corchetes) que corresponde al director a borrar: ", scanner);

        if (deleteIndex <= 0 || deleteIndex > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
                Director removeDirector = directors.get(deleteIndex-1);
                if (removeDirector.getFilms().isEmpty()) {
                    controller.removeDirector(removeDirector);
                } else {
                    System.err.println("ERROR. El director tiene películas asociadas.");
                }
            } catch (IndexOutOfBoundsException exp) {
                System.err.println("ERROR. Índice incorrecto.");
            }
        }
    }

    private void modifyDirector() {
        int i = 1;
        List<Director> directors = controller.getDirectors();
        if (directors == null || directors.isEmpty()) { System.err.println("No hay directores dados de alta."); return; }

        System.out.println("Listado de directores: ");
        for (Director director : directors) {
            System.out.println("\t["+(i++)+"] " + director.getName());
        }

        int index = CMUtils.askForInteger("Introduzca el número (entre corchetes) que corresponde al director a modificar: ", scanner);
        if (index <= 0 || index > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
            /*
                private Date birthdate;
                private String nationality;
                private String job;
            */

                Director selectedDirector = directors.get(index - 1);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                System.out.print("Fecha de nacimiento del director en formato dd/MM/yyyy (actual " + df.format(selectedDirector.getBirthdate()) + " (Intro para valor actual)): ");
                String change = scanner.nextLine();
                if (!change.equals("")) {
                    boolean exit = true;
                    do {
                        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date = sourceFormat.parse(change);
                            selectedDirector.setBirthdate(date);

                        } catch (ParseException exception) {
                            System.err.println("Fecha introducida incorrecta. Introduzcala correctamente.");
                            exit = false;
                        }
                    } while (!exit);
                }

                System.out.print("Nacionalidad del director (actual " + selectedDirector.getNationality() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedDirector.setNationality(change);

                System.out.print("Ocupación del director (actual " + selectedDirector.getJob() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedDirector.setJob(change);


            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Selección inválida.");
            }
        }
    }


    /**
     Relacionada con la opción "ACTORES"
     */
    private void actorsMenu(String menu) {
        boolean exit = false;
        String response;

        do {
            System.out.print(menu);
            do {  response = scanner.nextLine();   } while (response.isEmpty());

            /*  .append("\n\t1) Nuevo actor")
                .append("\n\t2) Eliminar actor")
                .append("\n\t3) Modificar actor")
                .append("\n\t4) Listar películas de un actor") */
            switch(response){

                case "1": newActor();       break;
                case "2": removeActor();    break;
                case "3": modifyActor();    break;
                case "4": listActorMovies();    break;
                case "V": case "v": exit = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!exit);
    }

    private void newActor() {
        System.out.println("Introduzca los datos del actor: ");

        Actor actor = new Actor();

        // Nombre
        System.out.print("Nombre del actor: ");
        actor.setName(scanner.nextLine());

        // Fecha de nacimiento
        boolean exit = true;
        do {
            System.out.print("Fecha de nacimiento del actor, en formato dd/MM/yyyy: ");

            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                String stringDate = scanner.nextLine();
                Date date = sourceFormat.parse(stringDate);
                actor.setBirthdate(date);

            } catch (ParseException exception) {
                System.err.println("Fecha introducida incorrecta. Introduzcala correctamente.");
                exit = false;
            }
        } while (!exit);

        // Nacionalidad
        System.out.print("Nacionalidad del actor: ");
        actor.setNationality(scanner.nextLine());

        // Ocupación
        actor.setDebutYear(CMUtils.askForInteger("Año de debut del actor: ", scanner));

        // Películas del actor.
        List<String> films = new ArrayList<>();
        exit = false;
        do {
            System.out.print("\tNombre de la película: ");
            films.add(scanner.nextLine());

            System.out.print("¿Desea añadir más películas? (S/n)");
            String exitAsk = scanner.nextLine();

            if (!"sS".contains(exitAsk)) exit = true;
        } while (!exit);
        actor.setFilms(films);

        // Guardamos en la colección.
        controller.addActorToCollection(actor);
    }

    private void removeActor() {
        int i = 1;
        List<Actor> actors = controller.getActors();
        if (actors == null || actors.isEmpty()) { System.err.println("No hay actores dados de alta para eliminar."); return; }

        System.out.println("Listado de directores: ");
        for (Actor actor : actors) {
            System.out.println("\t["+(i++)+"] " + actor.getName());
        }

        int deleteIndex = CMUtils.askForInteger("Introduzca el número (entre corchetes) que corresponde al actor a borrar: ", scanner);
        if (deleteIndex <= 0 || deleteIndex > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
                Actor removeActor = actors.get(deleteIndex-1);
                if (removeActor.getFilms().isEmpty()) {
                    controller.removeActor(removeActor);
                } else {
                    System.err.println("ERROR. El actor tiene películas asociadas.");
                }
            } catch (IndexOutOfBoundsException exp) {
                System.err.println("ERROR. Índice incorrecto.");
            }
        }
    }

    private void modifyActor() {
        int i = 1;
        List<Actor> actors = controller.getActors();
        if (actors == null || actors.isEmpty()) { System.err.println("No hay actores dados de alta."); return; }

        System.out.println("Listado de directores: ");
        for (Actor actor : actors) {
            System.out.println("\t["+(i++)+"] " + actor.getName());
        }

        int index = CMUtils.askForInteger("Introduzca el número (entre corchetes) que corresponde al director a modificar: ", scanner);
        if (index <= 0 || index > i)
            System.err.println("ERROR. Índice incorrecto.");
        else {
            try {
            /*
                private Date birthdate;
                private String nationality;
                private String job;
            */

                Actor selectedActor = actors.get(index - 1);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                System.out.print("Fecha de nacimiento del director en formato dd/MM/yyyy (actual " + df.format(selectedActor.getBirthdate()) + " (Intro para valor actual)): ");
                String change = scanner.nextLine();
                if (!change.equals("")) {
                    boolean exit = true;
                    do {
                        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date = sourceFormat.parse(change);
                            selectedActor.setBirthdate(date);

                        } catch (ParseException exception) {
                            System.err.println("Fecha introducida incorrecta. Introduzcala correctamente.");
                            exit = false;
                        }
                    } while (!exit);
                }

                System.out.print("Nacionalidad del director (actual " + selectedActor.getNationality() + " (Intro para valor actual)): ");
                change = scanner.nextLine();
                if (!change.equals("")) selectedActor.setNationality(change);

                int year = CMUtils.askForInteger("Año de debut del actor (actual " + selectedActor.getDebutYear() + " (Intro para valor actual)): ", scanner);
                if (!change.equals("")) selectedActor.setDebutYear(year);


            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Selección inválida.");
            }
        }
    }

    private void listActorMovies() {
        List<Actor> actors = controller.getActors();
        if (actors == null || actors.isEmpty()) { System.err.println("No hay actores a mostrar."); return; }

        System.out.print("¿Desea seleccionar el actor por lista o nombre? L/N");
        String exitAsk = scanner.nextLine();

        Actor selectedActor = null;

        if ("Ll".contains(exitAsk)) {
            // Mostramos la lista.

            int i = 1;

            System.out.println("Listado de actores: ");
            for (Actor actor : actors) {
                System.out.println("\t["+(i++)+"] " + actor.getName());
            }

            int index = CMUtils.askForInteger("Introduzca el número del actor sobre el que desea mostrar: ", scanner);
            if (index <= 0 || index > i)
                System.err.println("ERROR. Índice incorrecto.");
            else {
                try {
                    selectedActor = actors.get(index - 1);
                } catch (IndexOutOfBoundsException exp) {
                    System.err.println("ERROR. Índice incorrecto.");
                }
            }
        } else {
            System.out.print("Nombre del actor: ");
            String actorName = scanner.nextLine();

            for (Actor actor : controller.getActors()) {
                if (actor.getName().contains(actorName)) {
                    selectedActor = actor;
                    break;
                }
            }

            if (selectedActor == null) {
                System.err.println("ERROR: No hay actores con ese nombre.");
                return;
            }
        }

        // Comprobamos que tiene películas.
        if (selectedActor.getFilms() == null || selectedActor.getFilms().isEmpty()) {
            System.err.println("ERROR. El actor seleccionado no tiene películas.");
            return;
        }

        System.out.printf(Constants.FILM_ACTOR_TABLE_FORMAT+"\n", "TÍTULO", "AÑO", "DURACION", "PAÍS", "GÉNERO");
        for (String filmName : selectedActor.getFilms()) {
            System.out.println(filmName);

            Film film = controller.getFilmByName(filmName);
            film = (film == null) ? new Film(filmName) : film;

            // título, año, duración, país y género.
            System.out.printf(Constants.FILM_ACTOR_TABLE_FORMAT, film.getName(), film.getYear(), film.getDuration(), film.getCountry(), film.getGenre());
        }
    }


    /**
     Relacionada con la opción "LISTADO"
     */
    private void listMenu(String menu) {
        boolean exit = false;
        String response;

        do {
            System.out.print(menu);
            do {  response = scanner.nextLine();   } while (response.isEmpty());

            /*  1) Mostrar películas alfabeticamente
                2) Mostrar directores por nacionalidad y edad
                3) Mostrar actores por debut y nombre */
            switch(response){

                case "1": listFilmsAlph(); break;
                case "2": listDirectorsNac_Age();        break;
                case "3": listActorsName_Debut();        break;
                case "V": case "v": exit = true;         break;
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        } while (!exit);
    }

    private void listFilmsAlph() {
        if (controller.getFilms() == null ||controller.getFilms().isEmpty()) {
            System.err.println("No hay regitros que mostrar");
            return;
        }

        System.out.printf(Constants.FILM_ACTOR_TABLE_FORMAT+"\n", "TÍTULO", "AÑO", "DURACION", "PAÍS", "GÉNERO");
        for (Film film : controller.getSortedFilmsAlph()) {
            System.out.printf(Constants.FILM_ACTOR_TABLE_FORMAT+"\n", film.getName(), film.getYear(), film.getDuration(), film.getCountry(), film.getGenre());
        }
    }

    private void listDirectorsNac_Age() {
        if (controller.getDirectors() == null ||controller.getDirectors().isEmpty()) {
            System.err.println("No hay regitros que mostrar");
            return;
        }

        System.out.printf(Constants.DIRECTOR_TABLE_FORMAT+"\n", "NOMBRE", "FECHA NACIMIENTO", "NACIONALIDAD", "OCUPACIÓN", "PELÍCULAS");

        for (Director director : controller.getSortedFilmsNationalityAge()) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            System.out.printf(Constants.DIRECTOR_TABLE_FORMAT+"\n", director.getName(),
                    ((director.getBirthdate() != null) ? df.format(director.getBirthdate()) : "No date"), director.getNationality(),
                    director.getJob(), String.join(", ", director.getFilms()));
        }
    }

    private void listActorsName_Debut() {
        if (controller.getActors() == null ||controller.getActors().isEmpty()) {
            System.err.println("No hay regitros que mostrar");
            return;
        }

        System.out.printf(Constants.ACTOR_TABLE_FORMAT+"\n", "NOMBRE", "FECHA NACIMIENTO", "NACIONALIDAD", "AÑO DEBUT", "PELÍCULAS");

        for (Actor actor : controller.getSortedActorsDebutYearAndName()) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

             System.out.printf(Constants.ACTOR_TABLE_FORMAT+"\n",
                     actor.getName(),
                     ((actor.getBirthdate() != null) ? df.format(actor.getBirthdate()) : "No date"),
                     (actor.getNationality() == null) ? "No Nationality" :actor.getNationality(),
                     ((actor.getDebutYear() > 0) ? actor.getDebutYear() : "No year"),
                     String.join(", ", actor.getFilms()));
        }
    }

}
