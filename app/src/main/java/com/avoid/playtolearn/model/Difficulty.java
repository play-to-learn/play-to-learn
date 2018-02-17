package com.avoid.playtolearn.model;

import java.io.Serializable;

public enum Difficulty implements Serializable {
    Very_easy(1, "Very easy", 1),
    Easy(2, "Easy", 2),
    Medium(3, "Medium", 3),
    Hard(4, "Hard", 4),
    Very_hard(5, "Very hard", 5),
    Expert(6, "Expert", 6);

    private int id;
    private String name;
    private int score;

    Difficulty(int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}