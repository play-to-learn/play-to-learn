package com.avoid.playtolearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.util.Controller;
import com.avoid.playtolearn.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    private Switch soundsSwitch;
    private Switch musicSwitch;
    private Switch hintsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.soundsSwitch = (Switch) findViewById(R.id.sound_switch);
        this.musicSwitch = (Switch) findViewById(R.id.music_switch);
        this.hintsSwitch = (Switch) findViewById(R.id.hints_switch);

        refreshUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void closeButtonOnClick(View view) {
        finish();
    }

    public void resetButtonOnClick(View view) {
        Session.currentSettings.setSoundsOn(Session.currentSettings.isSoundsOnDefault());
        Session.currentSettings.setMusicOn(Session.currentSettings.isMusicOnDefault());
        Session.currentSettings.setHintsOn(Session.currentSettings.isHintsOnDefault());
        Session.settingsHandler.saveSettings();

        Controller.applySoundSettings(SettingsActivity.this);
        Controller.applyMusicSettings(SettingsActivity.this);

        refreshUI();
    }

    public void okButtonOnClick(View view) {
        Session.settingsHandler.saveSettings();
        finish();
    }

    public void soundsSwitchOnClick(View view) {
        Session.currentSettings.setSoundsOn(this.soundsSwitch.isEnabled());
        Controller.applySoundSettings(SettingsActivity.this);
    }

    public void musicSwitchOnClick(View view) {
        Session.currentSettings.setMusicOn(this.musicSwitch.isEnabled());
        Controller.applyMusicSettings(SettingsActivity.this);
    }

    public void hintsSwitchOnClick(View view) {
        Session.currentSettings.setHintsOn(this.hintsSwitch.isEnabled());
    }

    public void refreshUI(){
        this.soundsSwitch.setChecked(Session.currentSettings.isSoundsOn());
        this.musicSwitch.setChecked(Session.currentSettings.isMusicOn());
        this.hintsSwitch.setChecked(Session.currentSettings.isHintsOn());
    }
}
