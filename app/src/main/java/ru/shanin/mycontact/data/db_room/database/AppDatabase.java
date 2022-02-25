package ru.shanin.mycontact.data.db_room.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;

@Database(entities = {RoomPeople.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomPeopleDao roomPeopleDao();
}
