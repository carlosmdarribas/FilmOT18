package com.carlosmdarribasapps.usal.model;

import java.util.Date;
import java.util.List;

public class Director {
    private String name;
    private Date birthdate;
    private String nationality;
    private String job;
    private List<Film> films;

    public Director(String name, Date birthdate, String nationality, String job, List<Film> films) {
        this.name = name;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.job = job;
        this.films = films;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public List<Film> getFilms() { return films; }
    public void setFilms(List<Film> films) { this.films = films; }
}
