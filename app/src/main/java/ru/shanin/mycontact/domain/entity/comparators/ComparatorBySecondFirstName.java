package ru.shanin.mycontact.domain.entity.comparators;

import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.People;

public class ComparatorBySecondFirstName implements Comparator<People> {

    @Override
    public int compare(People people1, People people2) {
        String fn1 = people1.getPeopleInfo().getFirstName();
        String fn2 = people2.getPeopleInfo().getFirstName();
        String sn1 = people1.getPeopleInfo().getSecondName();
        String sn2 = people2.getPeopleInfo().getSecondName();
        int result;
        result = sn1.compareTo(sn2);
        if (result != 0) return result;
        else return fn1.compareTo(fn2);
    }
}