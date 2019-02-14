package com.dfl.openipma.settings

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.service.AlarmManagerWrapper
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var alarmManagerWrapper: AlarmManagerWrapper

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