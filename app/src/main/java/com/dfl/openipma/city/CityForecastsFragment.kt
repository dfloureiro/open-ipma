package com.dfl.openipma.city

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dfl.openipma.R
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.base.BaseFragment
import kotlinx.android.synthetic.main.city_forecasts_fragment.*
import kotlinx.android.synthetic.main.error_layout.*
import javax.inject.Inject

class CityForecastsFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    @Inject
    lateinit var cityForecastAdapter: CityForecastsAdapter

    private lateinit var viewModel: CityForecastsViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cityId = arguments?.getInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY)
        if (cityId != null) {
            viewModel = ViewModelProviders.of(this, viewModeFactory).get(CityForecastsViewModel::class.java)
            when {
                viewModel.cityForecastsState.value == null -> {
                    viewModel.loadData(cityId)
                }
            }
        } else {
            throw IllegalArgumentException("Cannot get forecasts from null cityId")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.city_forecasts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city_recycler_view.apply {
            adapter = cityForecastAdapter
        }
        viewModel.cityForecastsState.observe(viewLifecycleOwner, Observer<CityForecastsViewModel.CityForecastsState> {
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
        //TODO change the strings to the baseMapper
        val backgroundColor = ContextCompat.getColor(view.context, uiModel.cardBackgroundColor)
        view.findViewById<CardView>(R.id.city_today).setCardBackgroundColor(backgroundColor)
        view.findViewById<ImageView>(R.id.city_card_weather_icon).setImageResource(uiModel.weatherTypeResourceId)
        view.findViewById<TextView>(R.id.city_card_weather_description).text = uiModel.weatherDescription
        val minTemp = "${uiModel.minTemperature}º"
        view.findViewById<TextView>(R.id.city_card_min_temp).text = minTemp
        val maxTemp = "${uiModel.maxTemperature}º"
        view.findViewById<TextView>(R.id.city_card_max_temp).text = maxTemp
        val precipitation = "${uiModel.precipitationProbability}%"
        view.findViewById<TextView>(R.id.city_card_precipitation).text = precipitation
        view.findViewById<TextView>(R.id.city_card_wind).text = uiModel.windSpeedDescription
        view.findViewById<TextView>(R.id.city_card_wind_direction).text = uiModel.windDirection
        view.findViewById<ImageView>(R.id.city_card_wind_direction_icon).rotation = uiModel.windRotation
        view.findViewById<CardView>(R.id.city_today).visibility = View.VISIBLE
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