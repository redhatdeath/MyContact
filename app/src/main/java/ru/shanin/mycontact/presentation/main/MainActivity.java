package ru.shanin.mycontact.presentation.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.data.NewData;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.presentation.about.AboutActivity;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private PeopleArrayListAdapter peopleListAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private void initView() {
        fab = findViewById(R.id.add_ten);
        recyclerView = findViewById(R.id.rv_people_list);

        peopleListAdapter = new PeopleArrayListAdapter(new PeopleDiffCallback());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        fab.setOnClickListener(v -> {
            for (int i = 0; i < 2; i++)
                viewModel.addNewPeople(NewData.newPeople());
        });

        viewModel.getPeopleList().observe(this, peoples -> {
            Objects.requireNonNull(getSupportActionBar()).setTitle("length = " + peoples.size());
            List<People> newList = peoples;
            peopleListAdapter.submitList(newList);
        });

        recyclerView.setAdapter(peopleListAdapter);

        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                PeopleArrayListAdapter.VIEW_TYPE_PEOPLE,
                PeopleArrayListAdapter.MAX_POOL_SIZE);
        peopleListAdapter.peopleClickListener = new PeopleArrayListAdapter.OnPeopleClickListener() {
            @Override
            public void onPeopleClick(People people) {
                Intent intent = AboutActivity.startAboutActivity(
                        getApplicationContext(), people
                );
                startActivity(intent);
            }
        };

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(peopleListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


}