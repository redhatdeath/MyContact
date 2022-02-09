package ru.shanin.mycontact.domain.usecases;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleGetByIdUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public People peopleGetById(int _id) {
        return repository.peopleGetById(_id);
    }
}