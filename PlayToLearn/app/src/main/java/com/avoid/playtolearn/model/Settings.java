package com.avoid.playtolearn.model;

import java.io.Serializable;

public class Settings implements Serializable{
    private final boolean soundsOnDefault;
    private boolean soundsOn;
    private final boolean musicOnDefault;
    private boolean musicOn;
    private final boolean hintsOnDefault;
    private boolean hintsOn;

    public Settings() {
        this.soundsOnDefault = true;
        this.soundsOn = true;
        this.musicOnDefault = true;
        this.musicOn = true;
        this.hintsOnDefault = true;
        this.hintsOn = true;
    }

    public boolean isSoundsOnDefault() {
        return soundsOnDefault;
    }

    public boolean isSoundsOn() {
        return soundsOn;
    }

    public void setSoundsOn(boolean soundsOn) {
        this.soundsOn = soundsOn;
    }

    public boolean isMusicOnDefault() {
        return musicOnDefault;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public boolean isHintsOnDefault() {
        return hintsOnDefault;
    }

    public boolean isHintsOn() {
        return hintsOn;
    }

    public void setHintsOn(boolean hintsOn) {
        this.hintsOn = hintsOn;
    }
}
