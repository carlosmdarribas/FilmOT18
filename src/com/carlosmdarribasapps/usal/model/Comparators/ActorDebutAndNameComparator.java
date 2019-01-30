//
//  ActorDebutAndNameComparator.java
//  FilmOT18
//
//  Created by Carlos Martin de Arribas on 27/12/18
//

package com.carlosmdarribasapps.usal.model.Comparators;

import com.carlosmdarribasapps.usal.model.Actor;

import java.util.Comparator;

public class ActorDebutAndNameComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (o1.getDebutYear() == o2.getDebutYear() || o1.getDebutYear() == -1 || o2.getDebutYear() == -1
                || o1.getDebutYear() == 0 || o1.getDebutYear() == 0-`´´Ç`)
            return o1.getName().compareTo(o2.getName());
        else
            return Integer.compare(o1.getDebutYear(), o2.getDebutYear());
    }
}
