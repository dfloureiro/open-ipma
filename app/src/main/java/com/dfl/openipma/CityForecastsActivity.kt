package com.dfl.openipma

import android.os.Bundle

class CityForecastsActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        supportActionBar?.also {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = intent.getStringExtra(CITY_NAME_BUNDLE_KEY)
        }

        if (savedInstanceState == null) {
            val cityId = intent.getIntExtra(CITY_ID_BUNDLE_KEY, 0)
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, CityForecastsFragment.newInstance(cityId))
                .commit()
        }
    }

    companion object {
        const val CITY_NAME_BUNDLE_KEY = "CITY_NAME_BUNDLE_KEY"
        const val CITY_ID_BUNDLE_KEY = "CITY_ID_BUNDLE_KEY"
    }
}