package com.dfl.openipma.home

import androidx.core.content.ContextCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dfl.common.currentLocationIconRotation
import com.dfl.openipma.R

class HomeForecastViewHolder(private val viewHolderView: View) : RecyclerView.ViewHolder(viewHolderView) {

    fun setViewHolderOnClickListener(onClickListener: View.OnClickListener) {
        viewHolderView.setOnClickListener(onClickListener)
    }

    fun setCityName(name: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_city_name).text = name
    }

    fun setMinTemperature(minTemperature: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_min_temp).text = minTemperature
    }

    fun setMaxTemperature(maxTemperature: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_max_temp).text = maxTemperature
    }

    fun setPrecipitation(precipitation: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_precipitation).text = precipitation
    }

    fun setWindRotation(rotation: Float) {
        viewHolderView.findViewById<ImageView>(R.id.home_card_weather_icon).rotation = rotation
    }

    fun setWeatherDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_weather_description).text = description
    }

    fun setWindSpeedDescription(description: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_wind).text = description
    }

    fun setIcon(resourceId: Int) {
        viewHolderView.findViewById<ImageView>(R.id.home_card_weather_icon).setImageResource(resourceId)
    }

    fun setBackgroundColor(colorId: Int) {
        ContextCompat.getColor(viewHolderView.context, colorId)
            .also { (viewHolderView as CardView).setCardBackgroundColor(it) }
    }

    fun setHasCurrentLocation(isCurrentLocation: Boolean) {
        if (isCurrentLocation) {
            viewHolderView.findViewById<ImageView>(R.id.home_card_current_location).rotation =
                currentLocationIconRotation
        }
        val visibility = when {
            isCurrentLocation -> View.VISIBLE
            else -> View.GONE
        }
        viewHolderView.findViewById<ImageView>(R.id.home_card_current_location).visibility = visibility
    }
}