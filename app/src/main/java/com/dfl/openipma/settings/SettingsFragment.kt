package com.dfl.openipma.settings

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.bskyb.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase.Companion.WEATHER_NOTIFICATION_KEY
import com.bskyb.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.service.AlarmManagerWrapper
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var alarmManagerWrapper: AlarmManagerWrapper

    @Inject
    lateinit var lastKnownLocationUseCase: HandleLastKnownLocationUseCase

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity = activity
        when {
            activity != null -> (activity.application as IpmaApplication).injector.inject(this)
            else -> throw KotlinNullPointerException("can't instantiate the injector without a valid activity")
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        if (lastKnownLocationUseCase.wasLastKnownTerritoryIdSet().not()) {
            preferenceScreen.findPreference(WEATHER_NOTIFICATION_KEY).also {
                it.isEnabled = false
                it.summary = getString(R.string.settings_weather_notification_preference_description_disabled)
            }
        }

        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener { _, _ ->
            context?.also {
                alarmManagerWrapper.scheduleAlarmWeatherService(it)
            }
        }
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}