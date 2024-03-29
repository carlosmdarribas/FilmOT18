//
//  FilmAlphabeticComparator.java
//  FilmOT18
//
//  Created by Carlos Martin de Arribas on 27/12/18
//

package com.carlosmdarribasapps.usal.model.Comparators;

import com.carlosmdarribasapps.usal.model.Film;

import java.util.Comparator;

public class FilmAlphabeticComparator implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getName().compareToIgnoreCase(film2.getName());
    }
}
