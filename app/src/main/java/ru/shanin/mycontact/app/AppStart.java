package ru.shanin.mycontact.app;

import android.app.Application;
import android.content.Context;

import ru.shanin.mycontact.data.db_room.database.AppDatabase;
import ru.shanin.mycontact.data.repositoryImpl.PeopleRoomRepositoryImpl;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleEditByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;


public class AppStart extends Application {
    public static final Boolean isLog = !false;

    public static PeopleRoomRepositoryImpl impl;
    public static PeopleGetByAllUseCase peopleGetByAll;
    public static PeopleGetByIdUseCase peopleGetById;
    public static PeopleAddNewUseCases peopleAddNew;
    public static PeopleEditByIdUseCase peopleEditById;
    public static PeopleDeleteByIdUseCase peopleDeleteByIdUseCase;

    public static AppDatabase database;

    static {
        impl = new PeopleRoomRepositoryImpl();
        peopleGetByAll = new PeopleGetByAllUseCase(impl);
        peopleGetById = new PeopleGetByIdUseCase(impl);
        peopleAddNew = new PeopleAddNewUseCases(impl);
        peopleEditById = new PeopleEditByIdUseCase(impl);
        peopleDeleteByIdUseCase = new PeopleDeleteByIdUseCase(impl);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = createDataBase(this);
    }

    private AppDatabase createDataBase(Context context) {
        AppDatabase result = AppDatabase.INSTANCE.getDatabase(context);
        return result;
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
