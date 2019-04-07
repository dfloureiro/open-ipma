package com.dfl.openipma.home

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.openipma.R
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.base.BaseFragment
import com.dfl.openipma.city.CityForecastsActivity
import com.dfl.openipma.service.AlarmManagerWrapper
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
    lateinit var alarmManagerWrapper: AlarmManagerWrapper
    @Inject
    lateinit var handleOnScreenOpenEvents: HandleOnScreenOpenEvents
    @Inject
    lateinit var getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home_toolbar_title)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
        if (viewModel.homeViewState.value == null) {
            loadDataWithCurrentLocation()
        }
        if (savedInstanceState == null) {
            handleOnScreenOpenEvents.logHomeScreenLaunch()
            activity?.also { alarmManagerWrapper.scheduleAlarmWeatherService(it.applicationContext) }
        }
        loadPrivacyPolicyDialog()
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
        if (getWeatherNotificationPreferencesUseCase.getPrivacyPolicyDialogShowed().not()) {
            context?.also {
                AlertDialog.Builder(it)
                    .setMessage(R.string.privacy_policy_message)
                    .setPositiveButton(R.string.privacy_policy_allow_button) { _, _ ->
                        getWeatherNotificationPreferencesUseCase.setAnalyticsStatus(
                            true
                        )
                    }
                    .setNegativeButton(R.string.privacy_policy_disallow_button) { _, _ ->
                        getWeatherNotificationPreferencesUseCase.setAnalyticsStatus(
                            false
                        )
                    }
                    .setOnDismissListener { getWeatherNotificationPreferencesUseCase.setPrivacyPolicyDialogShowed(true) }
                    .setCancelable(false)
                    .show()
            }
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
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { viewModel.loadData(it.result) }
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