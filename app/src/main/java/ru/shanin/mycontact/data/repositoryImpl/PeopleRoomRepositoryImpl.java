package ru.shanin.mycontact.data.repositoryImpl;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;
import ru.shanin.mycontact.data.mapper.EntityMapper;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleRoomRepositoryImpl implements PeopleDomainRepository {
    private final RoomPeopleDao roomPeopleDao;
    private final MutableLiveData<ArrayList<People>> dataLiveData;


    public PeopleRoomRepositoryImpl(RoomPeopleDao roomPeopleDao) {
        this.roomPeopleDao = roomPeopleDao;
        dataLiveData = new MutableLiveData<>();
    }

    private void update() {
        ArrayList<RoomPeople> roomPeopleData = roomPeopleDao.roomPeopleGetAll();
        ArrayList<People> data = new ArrayList<>();
        for (RoomPeople roomPeople : roomPeopleData)
            data.add(EntityMapper.toPeople(roomPeople));
        dataLiveData.postValue(new ArrayList<>(data));
        if (AppStart.isLog) {
            Log.w("PeopleRoomRepositoryImpl", data.size() + "\n");
        }
    }

    @Override
    public void peopleAddNew(People people) {
        Thread t = new Thread(
                () -> {
                    synchronized (roomPeopleDao) {
                        roomPeopleDao.roomPeopleAddNew(
                                EntityMapper.toRoomPeople(
                                        people
                                )
                        );
                    }
                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();
    }

    @Override
    public void peopleEditById(People people) {
        Thread t = new Thread(
                () -> {
                    synchronized (roomPeopleDao) {
                        roomPeopleDao.roomPeopleEditById(
                                EntityMapper.toRoomPeople(
                                        people
                                )
                        );
                    }
                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();
    }

    @Override
    public void peopleDeleteById(People people) {
        Thread t = new Thread(
                () -> {
                    synchronized (roomPeopleDao) {
                        roomPeopleDao.roomPeopleDeleteById(
                                EntityMapper.toRoomPeople(
                                        people
                                )
                        );
                    }
                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();
    }


    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return dataLiveData;
    }

    @Override
    public People peopleGetById(int _id) {
        return EntityMapper.toPeople(
                roomPeopleDao.roomPeopleGetById(
                        _id
                )
        );
    }
}
