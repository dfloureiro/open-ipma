package com.dfl.openipma.earthquake

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R
import com.dfl.openipma.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EarthquakesFragment : BaseFragment(), OnMapReadyCallback {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Sismos"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.earthquakes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.earthquakes_map) as SupportMapFragment).also { it.getMapAsync(this) }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val sydney = LatLng(-33.852, 151.211)
        googleMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val lisbon = LatLng(38.766667, -9.15)
        googleMap!!.addMarker(MarkerOptions().position(lisbon).title("Marker in Lisbon"))

        googleMap.setOnMarkerClickListener { marker ->
            true
        }
    }

    companion object {
        fun newInstance(): EarthquakesFragment {
            return EarthquakesFragment()
        }
    }
}