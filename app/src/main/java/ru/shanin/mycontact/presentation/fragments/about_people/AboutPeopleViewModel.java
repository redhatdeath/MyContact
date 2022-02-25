package ru.shanin.mycontact.presentation.fragments.about_people;

import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import java.util.concurrent.atomic.AtomicReference;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;

public class AboutPeopleViewModel extends ViewModel {

    private final PeopleGetByIdUseCase getById =
            new PeopleGetByIdUseCase(AppStart.peopleRoomRepositoryImpl);

    protected People getPeople(int peopleId) {
        AtomicReference<People> people = null;
        AsyncTask.execute(
                () -> people.set(getById.peopleGetById(peopleId))
        );
        return people.get();
    }

}