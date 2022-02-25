package ru.shanin.mycontact.presentation.fragments.about_people;

import androidx.lifecycle.ViewModel;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;

public class AboutPeopleViewModel extends ViewModel {

    private final PeopleGetByIdUseCase getById =
            new PeopleGetByIdUseCase(AppStart.peopleRoomRepositoryImpl);

    protected People getPeople(int peopleId) {
        return getById.peopleGetById(peopleId);
    }
}