package com.avoid.playtolearn.models;

import java.io.Serializable;

public enum Category implements Serializable{
    Loops(1, "Loops"),
    Conditional_Structures(2, "Conditional structures");

    private int id;
    private String name;

    Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
