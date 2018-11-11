package com.dfl.openipma

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class HomeForecastsAdapter : RecyclerView.Adapter<HomeForecastViewHolder>() {

    private val forecasts = mutableListOf<ForecastUiModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeForecastViewHolder {
        return HomeForecastViewHolder((LayoutInflater.from(p0.context).inflate(R.layout.home_forecast_card, p0, false)))
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(p0: HomeForecastViewHolder, p1: Int) {
        forecasts[p1].also {
            p0.setCityName(it.cityName)
            p0.setMaxTemperature(it.maxTemperature)
            p0.setMinTemperature(it.minTemperature)
            p0.setPrecipitation(it.precipitationProbability)
        }
    }

    fun add(forecastsUiModel: List<ForecastUiModel>) {
        forecasts.addAll(forecastsUiModel)
        notifyItemRangeInserted(0, itemCount)
    }
}