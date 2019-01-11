package com.dfl.openipma

import android.os.Bundle

class MainActivity : OptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, HomeFragment.newInstance())
                .commit()
        }
    }
}