package ru.shanin.mycontact.data.db_room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = RoomPeople.NAME_TABLE,
        indices = {@Index(RoomPeople.COL_PEOPLE_ID)}
)
public class RoomPeople {

    public static final String NAME_TABLE = "Peoples";

    public static final String COL_PEOPLE_ID = "People_id";
    public static final String COL_FIRST_NAME = "First_name";
    public static final String COL_SECOND_NAME = "Second_name";
    public static final String COL_AGE = "Age";
    public static final String COL_PHONE = "Phone";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PATH_TO_PHOTO = "Path_to_photo";
    public static final String COL_LIST_OF_KNOWLEDGE = "List_of_knowledge";

    public static final int DEFAULT_ROW_ID = 1;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_PEOPLE_ID)
    public int peopleId;

    @ColumnInfo(name = COL_FIRST_NAME)
    public String firstName;

    @ColumnInfo(name = COL_SECOND_NAME)
    public String secondName;

    @ColumnInfo(name = COL_AGE)
    public int age;

    @ColumnInfo(name = COL_PHONE)
    public String phone;

    @ColumnInfo(name = COL_EMAIL)
    public String email;

    @ColumnInfo(name = COL_PATH_TO_PHOTO)
    public String pathToPhoto;

    @ColumnInfo(name = COL_LIST_OF_KNOWLEDGE)
    public String listOfKnowledge;

    public RoomPeople(
            String firstName,
            String secondName,
            int age,
            String phone,
            String email,
            String pathToPhoto,
            String listOfKnowledge
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.pathToPhoto = pathToPhoto;
        this.listOfKnowledge = listOfKnowledge;
        this.peopleId = DEFAULT_ROW_ID;
    }

    @Ignore
    public RoomPeople(
            String firstName,
            String secondName,
            int age,
            String phone,
            String email, String pathToPhoto,
            String listOfKnowledge,
            int peopleId
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.pathToPhoto = pathToPhoto;
        this.listOfKnowledge = listOfKnowledge;
        this.peopleId = peopleId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public String getListOfKnowledge() {
        return listOfKnowledge;
    }

}
