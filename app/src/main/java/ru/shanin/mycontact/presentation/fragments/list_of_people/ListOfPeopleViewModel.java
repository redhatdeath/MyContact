package ru.shanin.mycontact.presentation.fragments.list_of_people;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class ListOfPeopleViewModel extends ViewModel {


    private final PeopleGetByAllUseCase getAll =
            new PeopleGetByAllUseCase(AppStart.peopleRoomRepositoryImpl);

    protected MutableLiveData<ArrayList<People>> getPeopleList(){
        AtomicReference<MutableLiveData<ArrayList<People>>> result = new AtomicReference<>();
        AsyncTask.execute(
                () -> result.set(getAll.peopleGetByAll()));
        return result.get();
    }

    private final PeopleAddNewUseCases addNew =
            new PeopleAddNewUseCases(AppStart.peopleRoomRepositoryImpl);

    protected void addNewPeople(People people) {
        AsyncTask.execute(
                () -> addNew.peopleAddNew(people)
        );
    }
}