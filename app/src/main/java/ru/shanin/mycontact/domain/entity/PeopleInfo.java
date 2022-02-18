package ru.shanin.mycontact.domain.entity;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PeopleInfo {

    private final String firstName;
    private final String secondName;
    private final int age;
    private final String email;
    private final String phone;
    private final ArrayList<String> listOfKnowledge;
    private final String pathToPhoto;

    public PeopleInfo(
            String firstName,
            String secondName,
            String email,
            String phone,
            ArrayList<String> listOfKnowledge,
            String pathToPhoto
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.listOfKnowledge = listOfKnowledge;
        this.pathToPhoto = pathToPhoto;
        this.age = (int) (Math.random() * 61 + 5);
    }

    public PeopleInfo(
            String firstName,
            String secondName,
            int age,
            String email,
            String phone,
            ArrayList<String> listOfKnowledge,
            String pathToPhoto
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.listOfKnowledge = listOfKnowledge;
        this.pathToPhoto = pathToPhoto;
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + secondName;
    }

    public String toMyString() {
        return "PeopleInfo: " +
                " firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pathToPhoto='" + pathToPhoto + '\'' +
                ", listOfKnowledge=" + listOfKnowledge +
                " ";
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<String> getListOfKnowledge() {
        return listOfKnowledge;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

}








