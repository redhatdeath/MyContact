package ru.shanin.mycontact.presentation.fragments.about_people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.domain.entity.People;

public class AboutPeople extends Fragment {

    private AboutPeopleViewModel viewModel;
    private static final String ARGUMENT_PEOPLE_ID = "people id";
    private int peopleId;
    private People people;

    private int[] color = {0xAA55FF00, 0xAA550033, 0xAA550077, 0xAA5500AA, 0xAA5500FF};

    public static AboutPeople newInstance(
            int peopleId
    ) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PEOPLE_ID, peopleId);
        AboutPeople fragment = new AboutPeople();
        fragment.setArguments(args);
        return fragment;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_PEOPLE_ID))
            throw new RuntimeException("Argument '\''People Id'\'' is absent");
        peopleId = args.getInt(ARGUMENT_PEOPLE_ID);
    }

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        parseParams();
        initViewModel();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_about_people,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AboutPeopleViewModel.class);
        people = viewModel.getPeople(peopleId);
    }

    private void initView(View view) {
        int colorId = (people.getPeopleInfo().getAge()) / 10;
        if (colorId > 0 && colorId < 5)
            view.findViewById(R.id.layout_about_people).setBackgroundColor(color[colorId]);
        else
            view.findViewById(R.id.layout_about_people).setBackgroundColor(color[0]);
        String mDrawableName = people.getPeopleInfo().getPathToPhoto();
        int resID = view.getContext().getResources().getIdentifier(
                mDrawableName,
                "drawable",
                view.getContext().getPackageName()
        );
        ((ImageView) view.findViewById(R.id.photo))
                .setImageResource(resID);
        ((TextView) view.findViewById(R.id.tv_fn))
                .setText(people.getPeopleInfo().getFirstName());
        ((TextView) view.findViewById(R.id.tv_sn))
                .setText(people.getPeopleInfo().getSecondName());
        ((TextView) view.findViewById(R.id.tv_age))
                .setText(String.valueOf(people.getPeopleInfo().getAge()));
        ((TextView) view.findViewById(R.id.tv_email))
                .setText(people.getPeopleInfo().getEmail());
        ((TextView) view.findViewById(R.id.tv_phone))
                .setText(people.getPeopleInfo().getPhone());
        ((TextView) view.findViewById(R.id.tv_list_of_knowledge))
                .setText(people.getPeopleInfo().getListOfKnowledge().toString());
    }
}