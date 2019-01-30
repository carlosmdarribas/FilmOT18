//
//  CMFileHandler.java
//  FilmOT18
//
//  Created by Carlos Martin de Arribas on 27/12/18
//

package com.carlosmdarribasapps.usal.controller;

import com.carlosmdarribasapps.usal.model.Actor;
import com.carlosmdarribasapps.usal.model.Director;
import com.carlosmdarribasapps.usal.model.Film;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class CMFileHandler {

    /**
     * readDataFromTextFile()
     * @param String path. (Path to file.)
     * @return List<String> (Stores lines of the text file)
     * @throws IOException (If the file does not exists)
     */
    public List<String> readDataFromTextFile(String path) throws IOException {
        File file = new File(path);

        List<String> linesArray = null;
        if(Files.exists(file.toPath()))
            linesArray = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
        else
            throw new IOException("El archivo no existe");

        return linesArray;
    }

    /**
     * exportHTMLFile(String path, List<String> HTML)
     * @param path Path to file.
     * @param HTML HTML content, which will be written to file.
     */
    public void exportHTMLFile(String path, List<String> HTML) throws IOException {
        File f = new File(path);
        Files.write(f.toPath(), HTML, Charset.forName("UTF-8"));
    }

    /**
     * exportColumnedFile(String path, List<String> data)
     * @param path Path to file
     * @param data Data to be written to file.
     * @throws IOException
     */
    public void exportColumnedFile(String path, List<String> data) throws IOException {
        File f = new File(path);
        Files.write(f.toPath(), data, Charset.forName("UTF-8"));
    }

    /*
        PELICULAS
     */

    /**
     * filmsToBin(String path,List<Film>filmList)
     * @param path Path to file
     * @param filmList List of films to be written. Will be written in binary.
     * @throws IOException
     */
    public void filmsToBin(String path,List<Film>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    /**
     *
     * @param path Path to file
     * @return List<Film> which contains all the binary data read.
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * directorsToBin(String path,List<Director>filmList)
     * @param path Path to file
     * @param filmList List of films to be written in binary
     * @throws IOException
     */
    public void directorsToBin(String path,List<Director>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    /**
     * binToDirectors(String path)
     * @param path Path to file
     * @return List<Director> which contains all the binary data read.
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * actorsToBin(String path,List<Actor>filmList)
     * @param path Path to file
     * @param filmList List of films to be written in binary
     * @throws IOException
     */
    public void actorsToBin(String path,List<Actor>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    /**
     * binToActors(String path)
     * @param path Path to file
     * @return List<Actor> which contains all the binary data read.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Actor> binToActors(String path) throws IOException, ClassNotFoundException {
        List<Actor> actors;

        File f = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
        actors = (List<Actor>) ois.readObject();
        ois.close();

        return actors;
    }

    /**
     * checkIfFileExists(String fileName)
     * @param fileName Name of the file to be written.
     * @return true if found. False if 404.
     */
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
