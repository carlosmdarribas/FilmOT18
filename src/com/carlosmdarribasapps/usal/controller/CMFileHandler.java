package com.carlosmdarribasapps.usal.controller;

import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class CMFileHandler {

    public List<String> readDataFromTextFile(String path) throws IOException {
        File file = new File(path);

        List<String> linesArray = null;
        if(Files.exists(file.toPath()))
            linesArray = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
        else
            throw new IOException("El archivo no existe");

        return linesArray;
    }

    public void exportHTMLFile(String path, List<String> HTML) throws IOException {
        File f = new File(path);
        Files.write(f.toPath(), HTML, Charset.forName("UTF-8"));
    }

    public void exportColumnedFile(String path, List<String> data) throws IOException {
        File f = new File(path);
        Files.write(f.toPath(), data, Charset.forName("UTF-8"));
    }

    /*
        PELICULAS
     */
    public void filmsToBin(String path,List<Film>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    public List<Film> binToFilms(String path) throws IOException, ClassNotFoundException {
        List<Film> films;

        File f = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
        films = (List<Film>) ois.readObject();
        ois.close();

        return films;
    }


    /*
        DIRECTORES
     */
    public void directorsToBin(String path,List<Director>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    public List<Director> binToDirectors(String path) throws IOException, ClassNotFoundException {
        List<Director> directors;

        File f = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
        directors = (List<Director>) ois.readObject();
        ois.close();

        return directors;
    }

    /*
        ACTORES
     */
    public void actorsToBin(String path,List<Actor>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    public List<Actor> binToActors(String path) throws IOException, ClassNotFoundException {
        List<Actor> actors;

        File f = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
        actors = (List<Actor>) ois.readObject();
        ois.close();

        return actors;
    }

    public static boolean checkIfFileExists(String fileName) {
        boolean found = false;
        try {
            File file = new File(fileName);
            if (file.exists() && file.isFile())
            {
                found = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return found;
    }
}
