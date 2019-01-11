package com.dfl.openipma

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuInflater

@SuppressLint("Registered")
open class OptionsMenuActivity : BaseActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }
}