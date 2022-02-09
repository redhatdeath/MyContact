package ru.shanin.mycontact.app;

import android.app.Application;

import ru.shanin.mycontact.data.repositoryImpl.PeopleArrayListRepositoryImpl;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleEditByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;


public class AppStart extends Application {
    public static final Boolean isLog = !false;

    public static PeopleArrayListRepositoryImpl impl;
    public static PeopleGetByAllUseCase peopleGetByAll;
    public static PeopleGetByIdUseCase peopleGetById;
    public static PeopleAddNewUseCases peopleAddNew;
    public static PeopleEditByIdUseCase peopleEditById;
    public static PeopleDeleteByIdUseCase peopleDeleteByIdUseCase;


    @Override
    public void onCreate() {
        super.onCreate();
        impl = new PeopleArrayListRepositoryImpl();
        peopleGetByAll = new PeopleGetByAllUseCase(impl);
        peopleGetById = new PeopleGetByIdUseCase(impl);
        peopleAddNew = new PeopleAddNewUseCases(impl);
        peopleEditById = new PeopleEditByIdUseCase(impl);
        peopleDeleteByIdUseCase = new PeopleDeleteByIdUseCase(impl);
    }
}
