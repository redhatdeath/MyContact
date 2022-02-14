package ru.shanin.mycontact.presentation.fragments.list_of_people;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class ListOfPeopleViewModel extends ViewModel {

    private final PeopleGetByAllUseCase getAll = AppStart.peopleGetByAll;

    protected MutableLiveData<ArrayList<People>> getPeopleList() {
        return getAll.peopleGetByAll();
    }

    private final PeopleAddNewUseCases addNew = AppStart.peopleAddNew;

    protected void addNewPeople(People people) {
        addNew.peopleAddNew(people);
    }
}