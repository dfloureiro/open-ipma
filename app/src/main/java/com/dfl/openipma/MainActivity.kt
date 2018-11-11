package com.dfl.openipma

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dfl.domainipma.model.Forecast
import com.dfl.openipma.di.DaggerApplicationComponent
import com.dfl.openipma.di.IpmaModule
import com.dfl.openipma.di.NetworkModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /**@Inject
    lateinit var viewModeFactory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DaggerApplicationComponent.builder()
    .networkModule(NetworkModule())
    .ipmaModule(IpmaModule())
    .build()
    .inject(this)

    viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)

    viewModel.forecasts.observe(this,
    Observer<List<Forecast>> { it?.get(0)?.date }
    )
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, HomeFragment.newInstance())
                .commit()
        }
    }
}