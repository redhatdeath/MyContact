package ru.shanin.mycontact.data.repositoryImpl;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;
import ru.shanin.mycontact.data.mapper.EntityMapper;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleRoomRepositoryImpl implements PeopleDomainRepository {
    private final RoomPeopleDao roomPeopleDao;
    private final MutableLiveData<ArrayList<People>> dataLiveData;

    private static int autoIncrementId = 0;

    public PeopleRoomRepositoryImpl(
            RoomPeopleDao roomPeopleDao
    ) {
        this.roomPeopleDao = roomPeopleDao;
        updateAsyncTask();
        dataLiveData = new MutableLiveData<>();
    }

    private void updateAsyncTask() {
        AsyncTask.execute(
                this::update
        );
    }


    private void update() {
        synchronized (roomPeopleDao) {
            List<RoomPeople> roomPeopleData = roomPeopleDao.roomPeopleGetAll();
            autoIncrementId = roomPeopleData.size();
            if (AppStart.isLog) {
                Log.w("update", "autoIncrementId = " + autoIncrementId + "\n");
            }
            ArrayList<People> data = new ArrayList<>();
            for (RoomPeople roomPeople : roomPeopleData)
                data.add(EntityMapper.toPeople(roomPeople));
            dataLiveData.postValue(new ArrayList<>(data));
        }
    }

    @Override
    public void peopleAddNew(People people) {
        if (people.get_id() == People.UNDEFINED_ID)
            people.set_id(++autoIncrementId);
        synchronized (roomPeopleDao) {
            RoomPeople rp = EntityMapper.toRoomPeople(people);
            Log.w("PeopleRoomRepositoryImpl", (new Gson()).toJson(rp));
            roomPeopleDao.roomPeopleAddNew(rp);
        }
        updateAsyncTask();
    }

    @Override
    public void peopleEditById(People people) {
        synchronized (roomPeopleDao) {
            roomPeopleDao.roomPeopleEditById(
                    EntityMapper.toRoomPeople(people)
            );
        }
        updateAsyncTask();
    }

    @Override
    public void peopleDeleteById(People people) {
        synchronized (roomPeopleDao) {
            roomPeopleDao.roomPeopleDeleteById(
                    EntityMapper.toRoomPeople(people)
            );
        }
        updateAsyncTask();
    }


    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return dataLiveData;
    }

    @Override
    public People peopleGetById(int _id) {
        People people;
        synchronized (roomPeopleDao) {
            people = EntityMapper.toPeople(
                    roomPeopleDao.roomPeopleGetById(_id)
            );
        }
        return people;
    }
}
