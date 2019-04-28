package com.dfl.openipma.seismic

import android.os.Bundle
import android.view.Menu
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity

class SeismicActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SeismicFragment.newInstance())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu).also {
            menu.findItem(R.id.seismic).isVisible = false
        }
    }
}