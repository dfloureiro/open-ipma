package com.dfl.openipma.seismic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R
import com.dfl.openipma.ViewModelFactory
import com.dfl.openipma.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class SeismicFragment : BaseFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    private lateinit var viewModel: SeismicViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Sismos"
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(SeismicViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.earthquake_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.earthquakes_map) as SupportMapFragment).also { it.getMapAsync(this) }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.also { map ->

            when {
                viewModel.seismicState.value == null -> {
                    viewModel.loadData()
                }
            }

            viewModel.seismicState.observe(viewLifecycleOwner, Observer<SeismicViewModel.SeismicState> {
                when {
                    it != null -> {
                        it.seismicUiModels.forEach { seismicUiModel ->
                            map.addMarker(
                                MarkerOptions().position(
                                    LatLng(
                                        seismicUiModel.latitude,
                                        seismicUiModel.longitude
                                    )
                                )
                            )
                        }
                    }
                }
            })

            val lisbon = LatLng(38.766667, -9.15)
            //map.addMarker(MarkerOptions().position(lisbon).title("Marker in Lisbon"))
            map.moveCamera(CameraUpdateFactory.newLatLng(lisbon))
            map.uiSettings.isZoomControlsEnabled = true

            /*map.setOnMarkerClickListener { marker ->
                true
            }*/
        }
    }

    companion object {
        fun newInstance(): SeismicFragment {
            return SeismicFragment()
        }
    }
}