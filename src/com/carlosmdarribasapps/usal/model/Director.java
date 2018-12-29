package com.carlosmdarribasapps.usal.model;

import com.carlosmdarribasapps.usal.utils.Constants;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Director implements Serializable {
    private String name;
    private Date birthdate;
    private String nationality;
    private String job;
    private List<String> films;

    public Director(String name, Date birthdate, String nationality, String job, List<String> films) {
        this.name = name;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.job = job;
        this.films = films;
    }

    public Director() { }

    public Director(String name) {
        this.name = name;
    }

    public String toFormattedString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return String.format(Constants.DIRECTOR_TABLE_FORMAT+"\n", this.getName(),
                ((this.getBirthdate() != null) ? df.format(this.getBirthdate()) : "No date"), this.getNationality(),
                this.getJob(), String.join(", ", this.getFilms()));
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public List<String> getFilms() { return films; }
    public void setFilms(List<String> films) { this.films = films; }
}
