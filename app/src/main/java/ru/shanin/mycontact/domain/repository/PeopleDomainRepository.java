package ru.shanin.mycontact.domain.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mycontact.domain.entity.People;

public interface PeopleDomainRepository {

    void peopleAddNew(People people);

    void peopleEditById(People people);

    void peopleDeleteById(People people);

    MutableLiveData<ArrayList<People>> peopleGetAll();

    MutableLiveData<People> peopleGetById(int _id);

}
