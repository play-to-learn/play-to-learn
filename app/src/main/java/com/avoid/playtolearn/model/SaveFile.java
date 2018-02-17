package com.avoid.playtolearn.model;

import android.content.Context;

import java.io.Serializable;

public class SaveFile implements Serializable{
    private Profile profile = null;
    private Board board = null;

    public SaveFile(Context context) {
        this.profile = new Profile();
        this.board = new Board(context);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
