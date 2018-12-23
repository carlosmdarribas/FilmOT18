package com.carlosmdarribasapps.usal.model;

/*
    Clase de Modelo de "Film".
    Guardará los datos que correspondan con las peliculas, y los métodos para utilizarlo.
 */

public class Film {
    private String name;
    private int year;
    private int duration;
    private String country;
    //private Director[] direction;
    private String guion;
    private String music;
    private String photography;


    public Film(String name, int year, int duration, String country, String guion, String music, String photography) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.country = country;
        this.guion = guion;
        this.music = music;
        this.photography = photography;
    }


    /*
        Getters y Setter
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getGuion() { return guion; }
    public void setGuion(String guion) { this.guion = guion; }

    public String getMusic() { return music; }
    public void setMusic(String music) { this.music = music; }

    public String getPhotography() { return photography; }
    public void setPhotography(String photography) { this.photography = photography; }
}
