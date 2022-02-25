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
    private final MutableLiveData<ArrayList<People>> peoplesListLiveData;
    private final MutableLiveData<People> peopleLiveData;

    private static int autoIncrementId = 0;

    public PeopleRoomRepositoryImpl(
            RoomPeopleDao roomPeopleDao
    ) {
        this.roomPeopleDao = roomPeopleDao;
        initAutoIncrementId();
        peoplesListLiveData = new MutableLiveData<>();
        peopleLiveData = new MutableLiveData<>();
    }


    private void initAutoIncrementId() {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                autoIncrementId = roomPeopleDao.roomPeopleGetMaxId();
                if (AppStart.isLog) {
                    Log.w("update", "autoIncrementId = " + autoIncrementId);
                }
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleAddNew(People people) {
        if (people.get_id() == People.UNDEFINED_ID)
            people.set_id(++autoIncrementId);
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                RoomPeople rp = EntityMapper.toRoomPeople(people);
                Log.w("PeopleRoomRepositoryImpl", (new Gson()).toJson(rp));
                roomPeopleDao.roomPeopleAddNew(rp);
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleEditById(People people) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleEditById(
                        EntityMapper.toRoomPeople(people)
                );
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleDeleteById(People people) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleDeleteById(
                        EntityMapper.toRoomPeople(people)
                );
            }
        });
        updatePeopleListAsyncTask();
    }

    private void updatePeopleListAsyncTask() {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                List<RoomPeople> roomPeopleData = roomPeopleDao.roomPeopleGetAll();
                ArrayList<People> data = new ArrayList<>();
                for (RoomPeople roomPeople : roomPeopleData)
                    data.add(EntityMapper.toPeople(roomPeople));
                peoplesListLiveData.postValue(new ArrayList<>(data));
            }
        });
    }


    private void findPeopleById(int _id) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                People people = EntityMapper.toPeople(
                        roomPeopleDao.roomPeopleGetById(_id)
                );
                peopleLiveData.postValue(people);
            }
        });

    }

    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return peoplesListLiveData;
    }

    @Override
    public MutableLiveData<People> peopleGetById(int _id) {
        findPeopleById(_id);
        return peopleLiveData;
    }
}
