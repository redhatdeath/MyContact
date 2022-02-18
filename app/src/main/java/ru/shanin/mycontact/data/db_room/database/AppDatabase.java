package ru.shanin.mycontact.data.db_room.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;

@Database(entities = {RoomPeople.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public abstract RoomPeopleDao roomPeopleDao();

    public AppDatabase getDatabase(Context context) {
        AppDatabase tempInstance = INSTANCE;
        if (tempInstance != null)
            return tempInstance;
        synchronized (this) {
            AppDatabase instance = Room
                    .databaseBuilder(
                            context,
                            AppDatabase.class,
                            AppDatabase.class.getSimpleName()
                    )
                    .build();
            INSTANCE = instance;
            return instance;
        }
    }
}
