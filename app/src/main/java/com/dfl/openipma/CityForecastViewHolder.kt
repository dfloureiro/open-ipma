package com.dfl.openipma

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class CityForecastViewHolder(private val viewHolderView: View) : RecyclerView.ViewHolder(viewHolderView) {

    fun setDay(day: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_day_of_week).text = day
    }

    fun setMinTemperature(minTemperature: String) {
        val text = "${minTemperature}º"
        viewHolderView.findViewById<TextView>(R.id.city_card_min_temp).text = text
    }

    fun setMaxTemperature(maxTemperature: String) {
        val text = "${maxTemperature}º"
        viewHolderView.findViewById<TextView>(R.id.city_card_max_temp).text = text
    }

    fun setPrecipitation(precipitation: String) {
        val text = "$precipitation%"
        viewHolderView.findViewById<TextView>(R.id.city_card_precipitation).text = text
    }

    fun setWeatherDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_weather_description).text = description
    }

    fun setWindSpeedDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_wind).text = description
    }

    fun setIcon(resourceId: Int) {
        viewHolderView.findViewById<ImageView>(R.id.city_card_weather_icon).setImageResource(resourceId)
    }

    fun setBackgroundColor(colorId: Int) {
        ContextCompat.getColor(viewHolderView.context, colorId)
            .also { (viewHolderView as CardView).setCardBackgroundColor(it) }
    }

    fun setTemperatureStatusDescription(resourceId: Int) {
        val description = viewHolderView.context.getString(resourceId)
        viewHolderView.findViewById<TextView>(R.id.city_card_temp_status).text = description
    }
}