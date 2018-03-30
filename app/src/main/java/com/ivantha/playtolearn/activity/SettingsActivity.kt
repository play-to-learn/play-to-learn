package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        soundSwitch.setOnClickListener({
            Session.currentSettings.isSoundsOn = soundSwitch.isEnabled
            applySoundSettings()
        })

        musicSwitch.setOnClickListener({
            Session.currentSettings.isMusicOn = this.musicSwitch.isEnabled
            applyMusicSettings()
        })

        hintsSwitch.setOnClickListener({
            Session.currentSettings.isHintsOn = this.hintsSwitch.isEnabled
        })

        closeButton.setOnClickListener({
            finish()
        })

        resetButton.setOnClickListener({
            Session.currentSettings.isSoundsOn = Session.currentSettings.isSoundsOnDefault
            Session.currentSettings.isMusicOn = Session.currentSettings.isMusicOnDefault
            Session.currentSettings.isHintsOn = Session.currentSettings.isHintsOnDefault
            Session.settingsHelper.saveSettings()

            applySoundSettings()
            applyMusicSettings()

            refreshUI()
        })

        okButton.setOnClickListener({
            Session.settingsHelper.saveSettings()
            finish()
        })

        refreshUI()
    }

    private fun refreshUI() {
        soundSwitch.isChecked = Session.currentSettings.isSoundsOn
        musicSwitch.isChecked = Session.currentSettings.isMusicOn
        hintsSwitch.isChecked = Session.currentSettings.isHintsOn
    }

    private fun applyMusicSettings() {

    }

    private fun applySoundSettings() {

    }

}
