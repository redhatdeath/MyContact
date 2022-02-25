package ru.shanin.mycontact.data.db_room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.shanin.mycontact.data.db_room.entity.RoomPeople;

@Dao
public interface RoomPeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void roomPeopleAddNew(RoomPeople roomPeople);

    @Update
    void roomPeopleEditById(RoomPeople roomPeople);

    @Delete
    void roomPeopleDeleteById(RoomPeople roomPeople);

    @Query("SELECT * FROM Peoples ORDER BY First_name,Second_name ASC")
    List<RoomPeople> roomPeopleGetAll();

    @Query("SELECT * FROM Peoples WHERE People_id = :id")
    RoomPeople roomPeopleGetById(int id);

    @Query("SELECT MAX(People_id) FROM Peoples")
    int roomPeopleGetMaxId();

}
