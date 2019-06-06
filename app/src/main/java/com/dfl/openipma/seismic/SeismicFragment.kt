package com.dfl.openipma.seismic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.earthquake_fragment.*
import javax.inject.Inject


class SeismicFragment : BaseFragment(), OnMapReadyCallback, MapFragment {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    @Inject
    lateinit var seismicAdapter: SeismicAdapter

    private var map: GoogleMap? = null
    private lateinit var viewModel: SeismicViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.seismic_toolbar_title)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(SeismicViewModel::class.java)
        when {
            viewModel.seismicState.value == null -> {
                viewModel.loadData()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.earthquake_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.earthquakes_map) as SupportMapFragment).also { it.getMapAsync(this) }
        seismic_recycler_view.apply {
            adapter = seismicAdapter
        }

        viewModel.seismicState.observe(viewLifecycleOwner, Observer<SeismicViewModel.SeismicState> {
            when {
                it != null -> {
                    if (seismicAdapter.itemCount == 0) {
                        seismicAdapter.add(it.seismicUiModels)
                    }
                }
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap?.also { map ->
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

            map.uiSettings.isZoomControlsEnabled = true
            map.setOnMarkerClickListener { marker ->
                seismicAdapter.findItemWithCoordinates(marker.position.latitude, marker.position.longitude)?.also {
                    (seismic_recycler_view.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(it, 20)
                }
                false
            }
        }
    }

    override fun moveMapCamera(latitude: Double, longitude: Double, zoom: Float) {
        map?.also { googleMap ->
            googleMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        LatLng(latitude, longitude),
                        zoom,
                        0f,
                        0f
                    )
                )
            )
        }
    }

    companion object {
        fun newInstance(): SeismicFragment {
            return SeismicFragment()
        }
    }
}