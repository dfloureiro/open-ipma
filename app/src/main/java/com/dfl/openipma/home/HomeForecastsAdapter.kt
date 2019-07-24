package com.dfl.openipma.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R

class HomeForecastsAdapter(private val homeFragment: HomeFragment) : RecyclerView.Adapter<HomeForecastViewHolder>() {

    private val forecasts = mutableListOf<HomeForecastUiModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeForecastViewHolder {
        return HomeForecastViewHolder(
            (LayoutInflater.from(p0.context).inflate(
                R.layout.home_forecast_card,
                p0,
                false
            ))
        )
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(p0: HomeForecastViewHolder, p1: Int) {
        forecasts[p1].also { forecastUiModel ->
            // todo interface instead of fragment
            p0.setViewHolderOnClickListener(View.OnClickListener {
                homeFragment.loadForecastsForCity(
                    forecastUiModel.cityId,
                    forecastUiModel.cityName
                )
            })
            p0.setCityName(forecastUiModel.cityName)
            p0.setMaxTemperature(forecastUiModel.maxTemperature)
            p0.setMinTemperature(forecastUiModel.minTemperature)
            p0.setPrecipitation(forecastUiModel.precipitationProbability)
            // p0.setWindRotation(forecastUiModel.windRotation)
            p0.setWeatherDescription(forecastUiModel.weatherDescription)
            p0.setWindSpeedDescription(forecastUiModel.windSpeedDescription)
            p0.setIcon(forecastUiModel.weatherTypeResourceId)
            p0.setBackgroundColor(forecastUiModel.cardBackgroundColor)
            p0.setHasCurrentLocation(forecastUiModel.isClosestCity)
        }
    }

    fun add(homeForecastsUiModels: List<HomeForecastUiModel>) {
        forecasts.addAll(homeForecastsUiModels)
        notifyItemRangeInserted(0, itemCount)
    }
}