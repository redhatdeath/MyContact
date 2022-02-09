package ru.shanin.mycontact.domain.entity.comparators;

import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.People;

public class ComparatorBySecondName implements Comparator<People> {

    @Override
    public int compare(People people1, People people2) {
        String sn1 = people1.getPeopleInfo().getSecondName();
        String sn2 = people2.getPeopleInfo().getSecondName();
        return sn1.compareTo(sn2);
    }
}