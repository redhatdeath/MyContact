package ru.shanin.mycontact.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.comparators.ComparatorByFirstName;
import ru.shanin.mycontact.domain.entity.comparators.ComparatorByFirstSecondName;
import ru.shanin.mycontact.domain.entity.comparators.ComparatorBySecondFirstName;
import ru.shanin.mycontact.domain.entity.comparators.ComparatorBySecondName;

public class People {
    public static final int UNDEFINED_ID;

    public static final Comparator<People> byFN;
    public static final Comparator<People> bySN;
    public static final Comparator<People> byFSN;
    public static final Comparator<People> bySFN;


    static {
        UNDEFINED_ID = -1;
        byFN = new ComparatorByFirstName();
        bySN = new ComparatorBySecondName();
        byFSN = new ComparatorByFirstSecondName();
        bySFN = new ComparatorBySecondFirstName();
    }

    private final PeopleInfo peopleInfo;
    // private ArrayList<String> listOfWork;
    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public People(PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
        this._id = UNDEFINED_ID;
    }

    @NonNull
    @Override
    public String toString() {
        return peopleInfo.toString();
    }

    public String toMyString() {
        return "People { " +
                " _id=" + _id +
                ", peopleInfo=" + peopleInfo.toMyString() +
                " }";
    }

    public PeopleInfo getPeopleInfo() {
        return peopleInfo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        People guest = (People) obj;
        return
                _id == guest.get_id()
                        && (peopleInfo == guest.getPeopleInfo()
                        || (peopleInfo != null && peopleInfo.equals(guest.getPeopleInfo()))
                );
    }
}
