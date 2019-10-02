package com.dfl.openipma.city

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfl.domainanalytics.usecase.HandleOnScreenOpenEvents
import com.dfl.openipma.R
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.base.BaseFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.city_forecasts_fragment.*
import kotlinx.android.synthetic.main.error_layout.*

class CityForecastsFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    @Inject
    lateinit var cityForecastAdapter: CityForecastsAdapter
    @Inject
    lateinit var handleOnScreenOpenEvents: HandleOnScreenOpenEvents

    private lateinit var viewModel: CityForecastsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cityId = arguments?.getInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY)
        if (cityId != null) {
            if (savedInstanceState == null) {
                handleOnScreenOpenEvents.logCityForecastsScreenLaunch(cityId)
            }
            viewModel =
                ViewModelProviders.of(this, viewModeFactory).get(CityForecastsViewModel::class.java)
            when {
                viewModel.cityForecastsState.value == null -> {
                    viewModel.loadData(cityId)
                }
            }
        } else {
            throw IllegalArgumentException("Cannot get forecasts from null cityId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.city_forecasts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city_recycler_view.apply {
            adapter = cityForecastAdapter
        }
        viewModel.cityForecastsState.observe(
            viewLifecycleOwner,
            Observer<CityForecastsViewModel.CityForecastsState> {
                when {
                    it != null -> {
                        when {
                            it.loading -> city_progress_bar.visibility = View.VISIBLE
                            else -> city_progress_bar.visibility = View.GONE
                        }

                        when {
                            it.error -> city_error_layout.visibility = View.VISIBLE
                            else -> city_error_layout.visibility = View.GONE
                        }

                        it.todayUiModel?.apply { setTodayUiModel(view, this) }

                        if (cityForecastAdapter.itemCount == 0) {
                            cityForecastAdapter.add(it.forecastUiModels)
                        }
                    }
                }
            })

        error_retry_button.setOnClickListener { reloadData() }
    }

    private fun setTodayUiModel(view: View, uiModel: CityForecastUiModel) {
        view.also {
            val backgroundColor = ContextCompat.getColor(it.context, uiModel.cardBackgroundColor)
            it.findViewById<CardView>(R.id.city_today).setCardBackgroundColor(backgroundColor)
            it.findViewById<ImageView>(R.id.city_card_weather_icon)
                .setImageResource(uiModel.weatherTypeResourceId)
            it.findViewById<TextView>(R.id.city_card_weather_description).text =
                uiModel.weatherDescription
            it.findViewById<TextView>(R.id.city_card_min_temp).text = uiModel.minTemperature
            it.findViewById<TextView>(R.id.city_card_max_temp).text = uiModel.maxTemperature
            it.findViewById<TextView>(R.id.city_card_precipitation).text =
                uiModel.precipitationProbability
            it.findViewById<TextView>(R.id.city_card_wind).text = uiModel.windSpeedDescription
            it.findViewById<TextView>(R.id.city_card_wind_direction).text = uiModel.windDirection
            it.findViewById<ImageView>(R.id.city_card_wind_direction_icon).rotation =
                uiModel.windRotation
            it.findViewById<CardView>(R.id.city_today).visibility = View.VISIBLE
        }
    }

    private fun reloadData() {
        val cityId = arguments?.getInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY)
        when {
            cityId != null -> viewModel.loadData(cityId)
            else -> throw IllegalArgumentException("Cannot get forecasts from null cityId")
        }
    }

    companion object {
        fun newInstance(cityId: Int): CityForecastsFragment {
            val fragment = CityForecastsFragment()
            fragment.arguments = Bundle().also {
                it.putInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY, cityId)
            }
            return fragment
        }
    }
}
