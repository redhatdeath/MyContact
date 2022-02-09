package ru.shanin.mycontact.domain.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.shanin.mycontact.domain.entity.People;

public interface PeopleDomainRepository {

    void peopleAddNew(People people);

    void peopleEditById(People people);

    void peopleDeleteById(People people);

    MutableLiveData<List<People>> peopleGetAll();

    People peopleGetById(int _id);

}
