package ru.shanin.mycontact.presentation.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class MainViewModel extends ViewModel {

    private final PeopleAddNewUseCases addNew = AppStart.peopleAddNew;
    private final PeopleDeleteByIdUseCase delete = AppStart.peopleDeleteByIdUseCase;
    private final PeopleGetByAllUseCase getAll = AppStart.peopleGetByAll;

    private MutableLiveData<List<People>> peopleList;

    protected MutableLiveData<List<People>> getPeopleList() {
        peopleList = getAll.peopleGetByAll();
        return peopleList;
    }

    protected void deletePeople(People people) {
        delete.peopleDeleteById(people);

    }

    protected void addNewPeople(People people) {
        if (AppStart.isLog)
            Log.w("MainViewModel", "addNewPeople: " + people.toMyString());
        addNew.peopleAddNew(people);
    }

}
