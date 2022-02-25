package ru.shanin.mycontact.presentation.fragments.list_of_people;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class ListOfPeopleViewModel extends ViewModel {

    private MutableLiveData<ArrayList<People>> peoplesLiveData;
    private ArrayList<People> data;

    private final PeopleGetByAllUseCase getAll =
            new PeopleGetByAllUseCase(AppStart.peopleRoomRepositoryImpl);

    protected MutableLiveData<ArrayList<People>> getPeopleList() {
        peoplesLiveData = getAll.peopleGetByAll();
        data = peoplesLiveData.getValue();
        return peoplesLiveData;
    }

    private final PeopleAddNewUseCases addNew =
            new PeopleAddNewUseCases(AppStart.peopleRoomRepositoryImpl);

    protected void addNewPeople(People people) {
        addNew.peopleAddNew(people);
    }

    private final PeopleDeleteByIdUseCase delete =
            new PeopleDeleteByIdUseCase(AppStart.peopleRoomRepositoryImpl);

    protected void deletePeople(People people) {
        delete.peopleDeleteById(people);
    }

}