package com.dfl.openipma.base

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dfl.openipma.R
import com.dfl.openipma.about.AboutActivity
import com.dfl.openipma.seismic.SeismicActivity
import com.dfl.openipma.settings.SettingsActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.seismic -> {
                startActivity(Intent(this, SeismicActivity::class.java))
                true
            }
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}