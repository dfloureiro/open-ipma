package com.dfl.openipma

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import com.dfl.openipma.di.DaggerApplicationComponent
import com.dfl.openipma.di.IpmaModule
import com.dfl.openipma.di.NetworkModule
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.forecasts.observe(viewLifecycleOwner,
            Observer<List<Forecast>> { it?.get(0)?.date }
        )
        viewModel.cities.observe(viewLifecycleOwner,
            Observer<List<City>> { it?.get(0)?.name }
        )
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}