package ru.shanin.mycontact.domain.entity.comparators;

import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.People;

public class ComparatorByFirstName implements Comparator<People> {

    @Override
    public int compare(People people1, People people2) {
        String fn1 = people1.getPeopleInfo().getFirstName();
        String fn2 = people2.getPeopleInfo().getFirstName();
        return fn1.compareTo(fn2);
    }
}