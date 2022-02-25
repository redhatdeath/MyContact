package ru.shanin.mycontact.app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import ru.shanin.mycontact.data.db_room.database.AppDatabase;
import ru.shanin.mycontact.data.repositoryImpl.PeopleArrayListRepositoryImpl;
import ru.shanin.mycontact.data.repositoryImpl.PeopleRoomRepositoryImpl;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleEditByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;


public class AppStart extends Application {
    public static final Boolean isLog = !false;

    public static PeopleRoomRepositoryImpl peopleRoomRepositoryImpl;
    public static PeopleArrayListRepositoryImpl peopleArrayListRepositoryImpl;

    public static AppStart INSTANCE;
    private AppDatabase database;

    static {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        database = createDataBase(this);
        peopleRoomRepositoryImpl = new PeopleRoomRepositoryImpl(database.roomPeopleDao());
        peopleArrayListRepositoryImpl = new PeopleArrayListRepositoryImpl();
    }

    public static AppStart getINSTANCE() {
        return INSTANCE;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    private AppDatabase createDataBase(Context context) {
        AppDatabase tempDataBase = database;
        if (tempDataBase != null)
            return tempDataBase;
        else tempDataBase = Room
                .databaseBuilder(
                        context,
                        AppDatabase.class,
                        AppDatabase.class.getSimpleName()
                )
                .build();
        database = tempDataBase;
        return tempDataBase;
    }


}
