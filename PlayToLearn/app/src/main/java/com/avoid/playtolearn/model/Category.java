package com.avoid.playtolearn.model;

import java.io.Serializable;

public enum Category implements Serializable{
    Loops(1),
    Conditional_Structures(2);

    private int id;

    Category(int id){
        this.id = id;
    }
}
