package com.avoid.playtolearn.models;

import java.io.Serializable;

public enum BoardTileState implements Serializable {
    NOT_VISITED,
    VISITED,
    CURRENT,
    CORRECT_ANSWER,
    WRONG_ANSWER;
}
