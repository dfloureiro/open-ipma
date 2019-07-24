package com.dfl.openipma.city

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dfl.openipma.R

class CityForecastsAdapter : RecyclerView.Adapter<CityForecastViewHolder>() {

    private val forecasts = mutableListOf<CityForecastUiModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CityForecastViewHolder {
        return CityForecastViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.city_forecast_card,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(p0: CityForecastViewHolder, p1: Int) {
        forecasts[p1].also {
            p0.setDay(it.dayOfWeek)
            p0.setMinTemperature(it.minTemperature)
            p0.setMaxTemperature(it.maxTemperature)
            p0.setPrecipitation(it.precipitationProbability)
            p0.setWeatherDescription(it.weatherDescription)
            p0.setWindSpeedDescription(it.windSpeedDescription)
            p0.setIcon(it.weatherTypeResourceId)
            p0.setBackgroundColor(it.cardBackgroundColor)
            p0.setTemperatureStatusDescription(it.temperatureStatusStringId)
        }
    }

    fun add(forecastUiModel: List<CityForecastUiModel>) {
        forecasts.addAll(forecastUiModel)
        notifyItemRangeChanged(0, itemCount)
    }
}