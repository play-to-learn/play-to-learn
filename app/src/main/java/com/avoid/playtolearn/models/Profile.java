package com.avoid.playtolearn.models;

import java.io.Serializable;

public class Profile implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private int score;

    public Profile() {
        this.id = 0;
        this.firstName = "John";
        this.lastName = "Doe";
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
