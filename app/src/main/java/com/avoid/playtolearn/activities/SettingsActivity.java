package com.avoid.playtolearn.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.widgets.Switch;

public class SettingsActivity extends AppCompatActivity {
    private Switch soundsSwitch;
    private Switch musicSwitch;
    private Switch hintsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.soundsSwitch = findViewById(R.id.sound_switch);
        this.musicSwitch = findViewById(R.id.music_switch);
        this.hintsSwitch = findViewById(R.id.hints_switch);

        refreshUI();
    }

    public void closeButtonOnClick(View view) {
        finish();
    }

    public void resetButtonOnClick(View view) {
        Session.currentSettings.setSoundsOn(Session.currentSettings.isSoundsOnDefault());
        Session.currentSettings.setMusicOn(Session.currentSettings.isMusicOnDefault());
        Session.currentSettings.setHintsOn(Session.currentSettings.isHintsOnDefault());
        Session.settingsHelper.saveSettings();

        applySoundSettings(SettingsActivity.this);
        applyMusicSettings(SettingsActivity.this);

        refreshUI();
    }

    public void okButtonOnClick(View view) {
        Session.settingsHelper.saveSettings();
        finish();
    }

    public void soundsSwitchOnClick(View view) {
        Session.currentSettings.setSoundsOn(this.soundsSwitch.isEnabled());
        applySoundSettings(SettingsActivity.this);
    }

    public void musicSwitchOnClick(View view) {
        Session.currentSettings.setMusicOn(this.musicSwitch.isEnabled());
        applyMusicSettings(SettingsActivity.this);
    }

    public void hintsSwitchOnClick(View view) {
        Session.currentSettings.setHintsOn(this.hintsSwitch.isEnabled());
    }

    public void refreshUI(){
        this.soundsSwitch.setChecked(Session.currentSettings.isSoundsOn());
        this.musicSwitch.setChecked(Session.currentSettings.isMusicOn());
        this.hintsSwitch.setChecked(Session.currentSettings.isHintsOn());
    }


    private void applyMusicSettings(SettingsActivity settingsActivity) {

    }

    private void applySoundSettings(SettingsActivity settingsActivity) {

    }


}
