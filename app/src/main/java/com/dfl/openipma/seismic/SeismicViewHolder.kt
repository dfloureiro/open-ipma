package com.dfl.openipma.seismic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dfl.openipma.R

class SeismicViewHolder(private val viewHolderView: View) : RecyclerView.ViewHolder(viewHolderView) {

    fun setViewHolderOnClickListener(onClickListener: View.OnClickListener) {
        viewHolderView.setOnClickListener(onClickListener)
    }

    fun setMagnitud(magnitud: String) {
        viewHolderView.findViewById<TextView>(R.id.seismic_card_magnitud).text = magnitud
    }

    fun setRegion(region: String) {
        viewHolderView.findViewById<TextView>(R.id.seismic_card_region).text = region
    }

    fun setTime(time: String) {
        viewHolderView.findViewById<TextView>(R.id.seismic_card_time).text = time
    }

    fun setCoordinates(latitude: String, longitude: String) {
        val coordinates = "$latitude,$longitude"
        viewHolderView.findViewById<TextView>(R.id.seismic_card_coordinates).text = coordinates
    }

    fun setSensed(wasSent: Boolean) {
        val sensedText = when {
            wasSent -> viewHolderView.context.getString(R.string.seismic_felt_yes)
            else -> viewHolderView.context.getString(R.string.seismic_felt_no)
        }
        viewHolderView.findViewById<TextView>(R.id.seismic_card_felt).text = sensedText
    }
}