package com.dfl.openipma

import android.view.Menu
import android.view.MenuInflater

open class OptionsMenuActivity : BaseActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }
}