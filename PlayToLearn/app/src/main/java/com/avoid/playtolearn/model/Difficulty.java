package com.avoid.playtolearn.model;

import java.io.Serializable;

public enum Difficulty implements Serializable {
    Very_easy(1, "Very easy"),
    Easy(2, "Easy"),
    Medium(3, "Medium"),
    Hard(4, "Hard"),
    Very_hard(5, "Very hard"),
    Expert(6, "Expert");

    private int id;
    private String name;

    Difficulty(int id, String name){
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