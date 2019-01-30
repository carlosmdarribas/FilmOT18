//
//  FilmLibrary.java
//  FilmOT18
//
//  Created by Carlos Martin de Arribas on 27/12/18
//

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

    /**
     * Add film to the current list.
     * addFilm(Film film)
     * @param film
     */
    public void addFilm(Film film) {
        if (this.films == null || this.films.isEmpty())
            this.films = new ArrayList<Film>();

        this.films.add(film);
    }

    /**
     * add Director to the current list.
     * addDirector(Director director)
     * @param director
     */
    public void addDirector(Director director) {
        if (this.directors == null || this.directors.isEmpty())
            this.directors = new ArrayList<Director>();

        this.directors.add(director);
    }

    /**
     * add Actor to the current list.
     * addActor(Actor actor)
     * @param director
     */
    public void addActor(Actor actor) {
        if (this.actors == null || this.actors.isEmpty())
            this.actors = new ArrayList<Actor>();

        this.actors.add(actor);
    }

    /**
     * remove film from list.
     * @param film
     */
    public void removeFilm(Film film) {
        if (this.films == null || this.films.isEmpty()) return;

        this.films.remove(film);
    }

    /**
     * remove director from list
     * @param director
     */
    public void removeDirector(Director director) {
        if (this.directors == null || this.directors.isEmpty()) return;

        this.directors.remove(director);
    }

    /**
     * remove actor from list
     * @param actor
     */
    public void removeActor(Actor actor) {
        if (this.actors == null || this.actors.isEmpty()) return;

        this.actors.remove(actor);
    }

    // Exportamos en columnas.

    /**
     * return columned directors
     * @return
     */
    public List<String> getDirectorsInColumns() {
        if (this.directors == null ||this.directors.isEmpty())
            return null;

        List<String>columnedDirectors = new ArrayList<>();
        for(Director director : this.directors)
            columnedDirectors.add(director.toFormattedString());

        return columnedDirectors;
    }

    /**
     * Creates HTML and return it as List<String>
     * @return
     */
    public List<String> getFilmsInHTML() {
        if(null == this.films || this.films.isEmpty())
            return null;

        List<String>columnedFilms = new ArrayList<>();
        for(Film film : this.films)
            columnedFilms.add(film.toHtmlString());

        return columnedFilms;
    }

    // Getters / Setters
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
