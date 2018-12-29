package com.carlosmdarribasapps.usal.model.Comparators;

import com.carlosmdarribasapps.usal.model.Director;

import java.util.Comparator;
import java.util.Date;

// Comparador por nacionalidad y año de nacimiento.
public class DirectorNacionalityYearComparator implements Comparator<Director> {
    @Override
    public int compare(Director d1, Director d2) {
        System.out.println("d1 = [" + d1.getBirthdate() + "], d2 = [" + d2.getBirthdate() + "]");

        if (d1.getBirthdate() == null || d2.getBirthdate() == null ||  d1.getBirthdate().equals(d2.getBirthdate()))
            return d1.getNationality().compareTo(d2.getNationality());
        else
            return d1.getBirthdate().compareTo(d2.getBirthdate());
    }
}
