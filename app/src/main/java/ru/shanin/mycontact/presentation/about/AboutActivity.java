package ru.shanin.mycontact.presentation.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;

public class AboutActivity extends AppCompatActivity {

    private final static String ABOUT_PEOPLE = "About People";
    private People people;

    private ImageView photo;
    private TextView fn, sn, age, email, phone, list;


    public static Intent startAboutActivity(Context context, People people) {
        Gson gson = new Gson();
        String peopleGson = gson.toJson(people);
        Intent intent = new Intent(context, AboutActivity.class);
        intent.putExtra(ABOUT_PEOPLE, peopleGson);
        if (AppStart.isLog)
            Log.w("AboutActivity", peopleGson);
        return intent;
    }

    private void parseIntent() {
        if (!getIntent().hasExtra(ABOUT_PEOPLE))
            throw new RuntimeException("There is no People in input data");
        String peopleGson = getIntent().getStringExtra(ABOUT_PEOPLE);
        Gson gson = new Gson();
        people = gson.fromJson(peopleGson, People.class);
        if (AppStart.isLog)
            Log.w("AboutActivity", people.toMyString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        parseIntent();
        setValue();
    }

    private void initView() {
        photo = findViewById(R.id.photo);
        fn = findViewById(R.id.tv_fn);
        sn = findViewById(R.id.tv_sn);
        age = findViewById(R.id.tv_age);
        email = findViewById(R.id.tv_email);
        phone = findViewById(R.id.tv_phone);
        list = findViewById(R.id.tv_listOfKnowledge);
    }

    private void setValue() {
        // Из имени ресурса получить идентификатор
        // String mDrawableName = "name"; // файл name.png в папке drawable
        // int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        String mDrawableName = people.getPeopleInfo().getPathToPhoto();
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        photo.setImageResource(resID);
        fn.setText(people.getPeopleInfo().getFirstName());
        sn.setText(people.getPeopleInfo().getSecondName());
        age.setText(String.valueOf(people.getPeopleInfo().getAge()));
        email.setText(people.getPeopleInfo().getEmail());
        phone.setText(people.getPeopleInfo().getPhone());
        list.setText(people.getPeopleInfo().getListOfKnowledge().toString());
    }
}