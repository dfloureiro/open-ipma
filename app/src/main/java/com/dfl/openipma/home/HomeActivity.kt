package com.dfl.openipma.home

import android.os.Bundle
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity
import com.dfl.openipma.seismic.SeismicFragment

class HomeActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SeismicFragment.newInstance())
                .commit()
        }
    }
}