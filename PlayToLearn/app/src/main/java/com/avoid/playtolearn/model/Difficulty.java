package com.avoid.playtolearn.model;

import java.io.Serializable;

public enum Difficulty implements Serializable {
    Very_easy(1),
    Easy(2),
    Medium(3),
    Hard(4),
    Very_hard(5),
    Expert(6);

    private int id;

    Difficulty(int id){
        this.id = id;
    }
}