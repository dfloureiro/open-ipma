package com.dfl.openipma.city

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dfl.openipma.R

class CityForecastViewHolder(private val viewHolderView: View) :
    RecyclerView.ViewHolder(viewHolderView) {

    fun setDay(day: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_day_of_week).text = day
    }

    fun setMinTemperature(minTemperature: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_min_temp).text = minTemperature
    }

    fun setMaxTemperature(maxTemperature: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_max_temp).text = maxTemperature
    }

    fun setPrecipitation(precipitation: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_precipitation).text = precipitation
    }

    fun setWeatherDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_weather_description).text = description
    }

    fun setWindSpeedDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.city_card_wind).text = description
    }

    fun setIcon(resourceId: Int) {
        viewHolderView.findViewById<ImageView>(R.id.city_card_weather_icon)
            .setImageResource(resourceId)
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
