package com.dfl.openipma.settings

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.dfl.openipma.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}