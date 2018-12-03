package com.dfl.openipma

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class HomeForecastViewHolder(private val viewHolderView: View) : RecyclerView.ViewHolder(viewHolderView) {

    fun setViewHolderOnClickListener(onClickListener: View.OnClickListener) {
        viewHolderView.setOnClickListener(onClickListener)
    }

    fun setCityName(name: String) {
        viewHolderView.findViewById<TextView>(R.id.home_card_city_name).text = name
    }

    fun setMinTemperature(minTemperature: String) {
        val text = "${minTemperature}º"
        viewHolderView.findViewById<TextView>(R.id.home_card_min_temp).text = text
    }

    fun setMaxTemperature(maxTemperature: String) {
        val text = "${maxTemperature}º"
        viewHolderView.findViewById<TextView>(R.id.home_card_max_temp).text = text
    }

    fun setPrecipitation(precipitation: String) {
        val text = "$precipitation%"
        viewHolderView.findViewById<TextView>(R.id.home_card_precipitation).text = text
    }
}