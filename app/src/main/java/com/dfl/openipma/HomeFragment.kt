package com.dfl.openipma

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val homeForecastAdapter: HomeForecastsAdapter = HomeForecastsAdapter(this)

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home_toolbar_title)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
        when {
            viewModel.homeViewState.value == null -> loadDataWithCurrentLocation()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_recycler_view.apply {
            adapter = homeForecastAdapter
        }
        viewModel.homeViewState.observe(viewLifecycleOwner, Observer<HomeViewModel.HomeViewState> {
            when {
                it != null -> {
                    when {
                        it.loading -> home_progress_bar.visibility = View.VISIBLE
                        else -> home_progress_bar.visibility = View.GONE
                    }

                    when {
                        it.error -> home_error_text_view.visibility = View.VISIBLE
                        else -> home_error_text_view.visibility = View.GONE
                    }
                    if (homeForecastAdapter.itemCount == 0) {
                        homeForecastAdapter.add(it.forecastUiModels)
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_COARSE_LOCATION_ID && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadDataWithCurrentLocation()
        }
    }


    fun loadForecastsForCity(cityId: Int, cityName: String) {
        val intent = Intent(activity, CityForecastsActivity::class.java)
        intent.putExtra(CityForecastsActivity.CITY_ID_BUNDLE_KEY, cityId)
        intent.putExtra(CityForecastsActivity.CITY_NAME_BUNDLE_KEY, cityName)
        (activity as MainActivity).startActivity(intent)
    }

    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), ACCESS_COARSE_LOCATION_ID)
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun loadDataWithCurrentLocation() {
        if (hasLocationPermission()) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { viewModel.loadData(it.result) }
        } else {
            viewModel.loadData(null)
        }
    }

    companion object {
        private const val ACCESS_COARSE_LOCATION_ID = 1

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}