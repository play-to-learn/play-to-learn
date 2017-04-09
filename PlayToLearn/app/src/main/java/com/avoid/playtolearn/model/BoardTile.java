package com.avoid.playtolearn.model;

import java.io.Serializable;

public class BoardTile implements Serializable{
    private int row = 0;
    private int column = 0;
    private boolean hasPointer = false;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isHasPointer() {
        return hasPointer;
    }

    public void setHasPointer(boolean hasPointer) {
        this.hasPointer = hasPointer;
    }
}
