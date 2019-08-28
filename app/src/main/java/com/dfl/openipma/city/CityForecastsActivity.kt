package com.dfl.openipma.city

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dfl.domainpersistence.usecase.HandleFavouriteCitiesUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.base.OptionsMenuActivity
import javax.inject.Inject

class CityForecastsActivity : OptionsMenuActivity() {

    @Inject
    lateinit var handleFavouriteCitiesUseCase: HandleFavouriteCitiesUseCase

    private var cityId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as IpmaApplication).injector.inject(this)
        setContentView(R.layout.base_activity)

        supportActionBar?.also {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = intent.getStringExtra(CITY_NAME_BUNDLE_KEY)
        }

        val cityId = intent.getIntExtra(CITY_ID_BUNDLE_KEY, 0).also { cityId = it.toString() }
        if (savedInstanceState == null) {
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
            menu.findItem(R.id.favourite)?.apply {
                isVisible = true
                if (handleFavouriteCitiesUseCase.getFavouriteCities()?.contains(cityId) == true) {
                    setAsFavourite()
                } else {
                    setAsNotFavourite()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favourite) {
            if (item.isChecked.not()) {
                cityId?.also { handleFavouriteCitiesUseCase.addFavourite(it) }
                item.setAsFavourite()
            } else {
                cityId?.also { handleFavouriteCitiesUseCase.removeFavourite(it) }
                item.setAsNotFavourite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun MenuItem.setAsFavourite() {
        this.icon = getDrawable(R.drawable.ic_favorite_full)
        this.isChecked = true
    }

    private fun MenuItem.setAsNotFavourite() {
        this.icon = getDrawable(R.drawable.ic_favorite_empty)
        this.isChecked = false
    }

    companion object {
        const val CITY_NAME_BUNDLE_KEY = "CITY_NAME_BUNDLE_KEY"
        const val CITY_ID_BUNDLE_KEY = "CITY_ID_BUNDLE_KEY"
    }
}