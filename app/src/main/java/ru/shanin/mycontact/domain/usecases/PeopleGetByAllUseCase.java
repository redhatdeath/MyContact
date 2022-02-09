package ru.shanin.mycontact.domain.usecases;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleGetByAllUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByAllUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<People>> peopleGetByAll() {
        return repository.peopleGetAll();
    }
}