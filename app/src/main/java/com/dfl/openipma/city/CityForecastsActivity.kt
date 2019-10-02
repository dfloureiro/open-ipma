package com.dfl.openipma.city

import android.os.Bundle
import android.view.Menu
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity

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
                .replace(
                    android.R.id.content,
                    CityForecastsFragment.newInstance(cityId)
                )
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu).also {
            menu.findItem(R.id.seismic).isVisible = false
        }
    }

    companion object {
        const val CITY_NAME_BUNDLE_KEY = "CITY_NAME_BUNDLE_KEY"
        const val CITY_ID_BUNDLE_KEY = "CITY_ID_BUNDLE_KEY"
    }
}
