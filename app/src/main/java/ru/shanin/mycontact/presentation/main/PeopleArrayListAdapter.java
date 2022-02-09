package ru.shanin.mycontact.presentation.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Collections;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;


public class PeopleArrayListAdapter
        extends ListAdapter<People, PeopleViewHolder>
{

    public static final int MAX_POOL_SIZE = 20;
    public static final int VIEW_TYPE_PEOPLE = 100;
    public static final int VIEW_TYPE_NULL = 300;

    private static int count = 0;

    public OnPeopleClickListener peopleClickListener = null;

    public PeopleArrayListAdapter(PeopleDiffCallback diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (AppStart.isLog)
            Log.w("onCreateViewHolder", "onCreateViewHolder, count = " + (++count));
        int layout;
        switch (viewType) {
            case VIEW_TYPE_PEOPLE:
                layout = R.layout.activity_people;
                break;
            default:
                throw new RuntimeException("Unknown view type " + viewType);
        }
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new PeopleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder viewHolder, int position) {
        People people = getItem(position);
        viewHolder.tvFirstName.setText(String.valueOf(people.getPeopleInfo().getFirstName()));
        viewHolder.tvSecondName.setText(people.getPeopleInfo().getSecondName());
        String mDrawableName = people.getPeopleInfo().getPathToPhoto();
        //TODO  getResources() here
        Context context = viewHolder.itemView.getContext();
        int resID = context.getResources().getIdentifier(
                mDrawableName,
                "drawable",
                context.getPackageName()
        );
        viewHolder.imPhoto.setImageResource(resID);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleClickListener.onPeopleClick(people);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        People people = getItem(position);
        if (people != null)
            return VIEW_TYPE_PEOPLE;
        else return VIEW_TYPE_NULL;
    }

    interface OnPeopleClickListener {
        void onPeopleClick(People people);
    }
}