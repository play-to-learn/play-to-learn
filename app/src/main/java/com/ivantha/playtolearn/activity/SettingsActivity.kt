package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.SettingsHelper
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        soundSwitch.setOnClickListener({
            SettingsHelper.currentSettings!!.soundsOn = soundSwitch.isChecked
            applySoundSettings()
        })

        musicSwitch.setOnClickListener({
            SettingsHelper.currentSettings!!.musicOn = musicSwitch.isChecked
            applyMusicSettings()
        })

        hintsSwitch.setOnClickListener({
            SettingsHelper.currentSettings!!.hintsOn = hintsSwitch.isChecked
        })

        closeButton.setOnClickListener({
            finish()
        })

        resetButton.setOnClickListener({
            SettingsHelper.currentSettings!!.soundsOn = SettingsHelper.currentSettings!!.soundsOnDefault
            SettingsHelper.currentSettings!!.musicOn = SettingsHelper.currentSettings!!.musicOnDefault
            SettingsHelper.currentSettings!!.hintsOn = SettingsHelper.currentSettings!!.hintsOnDefault
            SettingsHelper.saveSettings(applicationContext)

            applySoundSettings()
            applyMusicSettings()

            refreshUI()
        })

        okButton.setOnClickListener({
            SettingsHelper.saveSettings(applicationContext)
            finish()
        })

        refreshUI()
    }

    private fun refreshUI() {
        soundSwitch.isChecked = SettingsHelper.currentSettings!!.soundsOn
        musicSwitch.isChecked = SettingsHelper.currentSettings!!.musicOn
        hintsSwitch.isChecked = SettingsHelper.currentSettings!!.hintsOn
    }

    private fun applyMusicSettings() {

    }

    private fun applySoundSettings() {

    }

}
