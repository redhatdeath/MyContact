package ru.shanin.mycontact.data.repositoryImpl;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.data.generate.NewData;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleArrayListRepositoryImpl implements PeopleDomainRepository {

    private final MutableLiveData<ArrayList<People>> dataLiveData;
    private final ArrayList<People> data;

    {
        data = new ArrayList<>();
        dataLiveData = new MutableLiveData<>();
    }

    private static int autoIncrementId = 0;

    {
        for (int i = 0; i < 5; i++) {
            peopleAddNew(
                    NewData.newPeople()
            );
        }
    }

    private void updateList() {
        dataLiveData.postValue(new ArrayList<>(data));
        if (AppStart.isLog) {
            Log.w("PeopleArrayListRepositoryImpl", data.size() + "\n");
        }
    }

    @Override
    public void peopleAddNew(People people) {
        if (people.get_id() == People.UNDEFINED_ID)
            people.set_id(autoIncrementId++);
        data.add(people);
        data.sort(People.byFSN);
        updateList();
    }

    @Override
    public void peopleEditById(People people) {
        People people_old = peopleGetById(people.get_id());
        peopleDeleteById(people_old);
        peopleAddNew(people);
    }

    @Override
    public void peopleDeleteById(People people) {
        data.remove(people);
        updateList();
    }

    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return dataLiveData;
    }

    @Override
    public People peopleGetById(int _id) {
        // TODO find by position in arraylist. not by id. need change
        People people = data.get(_id);
        if (people != null)
            return people;
        else throw new RuntimeException("There is no element with id = " + _id);
    }
}
