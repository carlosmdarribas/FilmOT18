package com.carlosmdarribasapps.usal.model;

/*
    Clase de Modelo de "Film".
    Guardará los datos que correspondan con las peliculas, y los métodos para utilizarlo.
 */

import java.io.Serializable;
import java.util.List;

public class Film implements Serializable {
    private String name;
    private int year;
    private int duration;
    private String country;
    private List<String> direction;
    private String guion;
    private String music;
    private String photography;
    private List<String> cast;
    private String producer;
    private String genre;
    private String synopsis;

    public Film(String name, int year, int duration, String country, List<String> direction, String guion, String music, String photography, List<String> cast, String producer, String genre, String synopsis) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.country = country;
        this.direction = direction;
        this.guion = guion;
        this.music = music;
        this.photography = photography;
        this.cast = cast;
        this.producer = producer;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public Film() {

    }

    public Film(String name) {
        this.name = name;
    }

    public String toHtmlString() {
        return String.format("<TR><TD>%s</TD><TD>%d</TD><TD>%d</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD><TD>%s</TD></TR>",
                this.name = name,
                this.year,
                this.duration,
                this.country,
                String.join(",", this.direction),
                this.guion,
                this.music,
                this.photography,
                String.join(",", this.cast),
                this.producer,
                this.genre,
                this.synopsis);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getDirection() {
        return direction;
    }

    public void setDirection(List<String> direction) {
        this.direction = direction;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getPhotography() {
        return photography;
    }

    public void setPhotography(String photography) {
        this.photography = photography;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
