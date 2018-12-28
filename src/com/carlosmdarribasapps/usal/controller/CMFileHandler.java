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


    public void filmsToBin(String path,List<Film>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    public void actorsToBin(String path,List<Actor>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
    }

    public void directorsToBin(String path,List<Director>filmList) throws IOException{
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        oos.writeObject(filmList);
        oos.close();
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
