package com.dfl.openipma.settings

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import com.dfl.domainpersistence.ANALYTICS_STATUS_KEY
import com.dfl.domainpersistence.NOTIFICATION_TIME_KEY
import com.dfl.domainpersistence.WEATHER_NOTIFICATION_KEY
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.service.JobSchedulerWrapper
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var jobSchedulerWrapper: JobSchedulerWrapper
    @Inject
    lateinit var lastKnownLocationUseCase: HandleLastKnownLocationUseCase
    @Inject
    lateinit var handleOnScreenOpenEvents: HandleOnScreenOpenEvents
    @Inject
    lateinit var handleOnSettingsChangeEvents: HandleOnSettingsChangeEvents

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity = activity
        when {
            activity != null -> (activity.application as IpmaApplication).injector.inject(this)
            else -> throw KotlinNullPointerException("can't instantiate the injector without a valid activity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            handleOnScreenOpenEvents.logSettingsScreenLaunch()
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

        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            context?.also {
                when (key) {
                    NOTIFICATION_TIME_KEY -> {
                        val time = sharedPreferences.getString(key, "")
                        if (time.isNullOrEmpty().not()) {
                            handleOnSettingsChangeEvents.logOnNotificationTimeChange(time!!)
                        }
                    }
                    WEATHER_NOTIFICATION_KEY ->
                        handleOnSettingsChangeEvents.logOnNotificationStatusChange(
                            sharedPreferences.getBoolean(key, true)
                        )
                    ANALYTICS_STATUS_KEY ->
                        handleOnSettingsChangeEvents.setAnalyticsStatus(sharedPreferences.getBoolean(key, true))
                }
                jobSchedulerWrapper.scheduleAlarmWeatherService(it)
            }
        }
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}