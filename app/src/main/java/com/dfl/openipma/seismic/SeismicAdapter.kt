package com.dfl.openipma.seismic

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R

class SeismicAdapter(private val mapFragment: MapFragment) : RecyclerView.Adapter<SeismicViewHolder>() {

    private val earthquakes = mutableListOf<SeismicUiModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SeismicViewHolder {
        return SeismicViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.seismic_card,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return earthquakes.size
    }

    override fun onBindViewHolder(p0: SeismicViewHolder, p1: Int) {
        earthquakes[p1].also { seismicUiModel ->
            p0.setViewHolderOnClickListener(View.OnClickListener {
                mapFragment.moveMapCamera(seismicUiModel.latitude, seismicUiModel.longitude)
            })

            p0.setMagnitud(seismicUiModel.magnitud)
            p0.setRegion(seismicUiModel.region)
            p0.setTime(seismicUiModel.time)
            p0.setCoordinates(seismicUiModel.latitude.toString(), seismicUiModel.longitude.toString())
            p0.setSensed(seismicUiModel.sensed)
        }
    }

    fun add(seismicUiModels: List<SeismicUiModel>) {
        earthquakes.addAll(seismicUiModels)
        notifyItemRangeInserted(0, itemCount)
    }

    fun findItemWithCoordinates(latitude: Double, longitude: Double): Int? {
        return earthquakes.find { it.latitude == latitude && it.longitude == longitude }.let {
            when {
                it != null -> earthquakes.indexOf(it)
                else -> null
            }
        }
    }
}