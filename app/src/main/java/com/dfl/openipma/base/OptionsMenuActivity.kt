package com.dfl.openipma.base

import android.annotation.SuppressLint
import android.view.Menu
import com.dfl.openipma.R

@SuppressLint("Registered")
open class OptionsMenuActivity : BaseActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
}