package com.carlosmdarribasapps.usal.model;

import java.util.ArrayList;
import java.util.List;

public class FilmLibrary {
    private List<Film> films;
    private List<Director> directors;
    private List<Actor> actors;

    public FilmLibrary() {}

    public FilmLibrary(List<Film> films, List<Director> directors, List<Actor> actors) {
        this.films = films;
        this.directors = directors;
        this.actors = actors;
    }

    public void addFilm(Film film) {
        if (this.films == null || this.films.isEmpty())
            this.films = new ArrayList<Film>();

        this.films.add(film);
    }

    public void addDirector(Director director) {
        if (this.directors == null || this.directors.isEmpty())
            this.directors = new ArrayList<Director>();

        this.directors.add(director);
    }

    public void addActor(Actor actor) {
        if (this.actors == null || this.actors.isEmpty())
            this.actors = new ArrayList<Actor>();

        this.actors.add(actor);
    }

    public void removeFilm(Film film) {
        if (this.films == null || this.films.isEmpty()) return;

        this.films.remove(film);
    }

    public void removeDirector(Director director) {
        if (this.directors == null || this.directors.isEmpty()) return;

        this.directors.remove(director);
    }

    public void removeActor(Actor actor) {
        if (this.actors == null || this.actors.isEmpty()) return;

        this.actors.remove(actor);
    }


    // Exportamos en columnas.
    public List<String> getDirectorsInColumns() {
        if (this.directors == null ||this.directors.isEmpty())
            return null;

        List<String>columnedDirectors = new ArrayList<>();
        for(Director director : this.directors)
            columnedDirectors.add(director.toFormatedString());

        return columnedDirectors;
    }

    public List<String> getFilmsInHTML() {
        if(null == this.films || this.films.isEmpty())
            return null;

        List<String>columnedFilms = new ArrayList<>();
        for(Film film : this.films)
            columnedFilms.add(film.toHtmlString());

        return columnedFilms;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
