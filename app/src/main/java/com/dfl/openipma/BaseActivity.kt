package com.dfl.openipma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.settings -> {
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