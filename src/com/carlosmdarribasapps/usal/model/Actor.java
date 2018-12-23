package com.carlosmdarribasapps.usal.model;

import java.util.Date;
import java.util.List;

public class Actor {
    private String name;
    private Date birthdate;
    private String nationality;
    private int year;
    private List<Film> films;

    public Actor(String name, Date birthdate, String nationality, int year, List<Film> films) {
        this.name = name;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.year = year;
        this.films = films;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public List<Film> getFilms() { return films; }
    public void setFilms(List<Film> films) { this.films = films; }
}
