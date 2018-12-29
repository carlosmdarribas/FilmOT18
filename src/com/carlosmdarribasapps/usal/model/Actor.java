package com.carlosmdarribasapps.usal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Actor implements Serializable {
    private String name;
    private Date birthdate;
    private String nationality;
    private int debutYear;
    private List<String> films;

    public Actor(String name, Date birthdate, String nationality, int year, List<String> films) {
        this.name = name;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.debutYear = year;
        this.films = films;
    }

    public Actor(String name) {
        this.name = name;
    }

    public Actor() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public int getDebutYear() { return debutYear; }
    public void setDebutYear(int year) { this.debutYear = year; }

    public List<String> getFilms() { return films; }
    public void setFilms(List<String> films) { this.films = films; }
}
