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
            Session.settingsHelper!!.currentSettings!!.soundsOn = soundSwitch.isChecked
            applySoundSettings()
        })

        musicSwitch.setOnClickListener({
            Session.settingsHelper!!.currentSettings!!.musicOn = musicSwitch.isChecked
            applyMusicSettings()
        })

        hintsSwitch.setOnClickListener({
            Session.settingsHelper!!.currentSettings!!.hintsOn = hintsSwitch.isChecked
        })

        closeButton.setOnClickListener({
            finish()
        })

        resetButton.setOnClickListener({
            Session.settingsHelper!!.currentSettings!!.soundsOn = Session.settingsHelper!!.currentSettings!!.soundsOnDefault
            Session.settingsHelper!!.currentSettings!!.musicOn = Session.settingsHelper!!.currentSettings!!.musicOnDefault
            Session.settingsHelper!!.currentSettings!!.hintsOn = Session.settingsHelper!!.currentSettings!!.hintsOnDefault
            Session.settingsHelper!!.saveSettings(applicationContext)

            applySoundSettings()
            applyMusicSettings()

            refreshUI()
        })

        okButton.setOnClickListener({
            Session.settingsHelper!!.saveSettings(applicationContext)
            finish()
        })

        refreshUI()
    }

    private fun refreshUI() {
        soundSwitch.isChecked = Session.settingsHelper!!.currentSettings!!.soundsOn
        musicSwitch.isChecked = Session.settingsHelper!!.currentSettings!!.musicOn
        hintsSwitch.isChecked = Session.settingsHelper!!.currentSettings!!.hintsOn
    }

    private fun applyMusicSettings() {

    }

    private fun applySoundSettings() {

    }

}
