package com.dfl.openipma.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.openipma.R
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.base.BaseFragment
import com.dfl.openipma.city.CityForecastsActivity
import com.dfl.openipma.service.JobSchedulerWrapper
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    @Inject
    lateinit var homeForecastAdapter: HomeForecastsAdapter
    @Inject
    lateinit var jobSchedulerWrapper: JobSchedulerWrapper
    @Inject
    lateinit var handleOnScreenOpenEvents: HandleOnScreenOpenEvents

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.home_toolbar_title)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
        if (viewModel.homeViewState.value == null) {
            loadDataWithCurrentLocation()
        }
        if (savedInstanceState == null) {
            handleOnScreenOpenEvents.logHomeScreenLaunch()
            activity?.also { jobSchedulerWrapper.scheduleAlarmWeatherService(it.applicationContext) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                    if (it.privacyPolicy) {
                        loadPrivacyPolicyDialog()
                    }

                    when {
                        it.loading -> home_progress_bar.visibility = View.VISIBLE
                        else -> home_progress_bar.visibility = View.GONE
                    }

                    when {
                        it.error -> home_error_layout.visibility = View.VISIBLE
                        else -> home_error_layout.visibility = View.GONE
                    }
                    if (homeForecastAdapter.itemCount == 0) {
                        homeForecastAdapter.add(it.homeForecastUiModels)
                    }
                }
            }
        })

        error_retry_button.setOnClickListener { loadDataWithCurrentLocation() }
    }

    fun loadForecastsForCity(cityId: Int, cityName: String) {
        val intent = Intent(activity, CityForecastsActivity::class.java)
        intent.putExtra(CityForecastsActivity.CITY_ID_BUNDLE_KEY, cityId)
        intent.putExtra(CityForecastsActivity.CITY_NAME_BUNDLE_KEY, cityName)
        (activity as HomeActivity).startActivity(intent)
    }

    private fun loadPrivacyPolicyDialog() {
        context?.also {
            AlertDialog.Builder(it)
                .setMessage(R.string.privacy_policy_message)
                .setPositiveButton(R.string.privacy_policy_allow_button) { _, _ ->
                    viewModel.setPrivacyPolicyPreferences(
                        true
                    )
                }
                .setNegativeButton(R.string.privacy_policy_disallow_button) { _, _ ->
                    viewModel.setPrivacyPolicyPreferences(
                        false
                    )
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun hasLocationPermission(): Boolean {
        val currentContext = context
        return when {
            currentContext != null -> return ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            else -> false
        }
    }

    @SuppressLint("MissingPermission")
    private fun loadDataWithCurrentLocation() {
        if (hasLocationPermission()) {
            try {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    viewModel.loadData(
                        it.result
                    )
                }
            } catch (e: Exception) {
                Log.e("HomeFragment", e.message.orEmpty())
                viewModel.loadData(null)
            }
        } else {
            viewModel.loadData(null)
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}