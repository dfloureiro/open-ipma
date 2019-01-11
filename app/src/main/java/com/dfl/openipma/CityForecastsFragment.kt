package com.dfl.openipma

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.city_forecasts_fragment.*
import javax.inject.Inject

class CityForecastsFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private val cityForecastAdapter: CityForecastsAdapter = CityForecastsAdapter()

    private lateinit var viewModel: CityForecastsViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cityId = arguments?.getInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY)
        if (cityId != null) {
            viewModel = ViewModelProviders.of(this, viewModeFactory).get(CityForecastsViewModel::class.java)
            when {
                viewModel.cityForecastsState.value == null -> {
                    viewModel.loadData(cityId)
                }
            }
        } else {
            throw IllegalArgumentException("Cannot get forecasts from null cityId")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.city_forecasts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city_recycler_view.apply {
            adapter = cityForecastAdapter
        }
        viewModel.cityForecastsState.observe(viewLifecycleOwner, Observer<CityForecastsViewModel.CityForecastsState> {
            when {
                it != null -> {
                    when {
                        it.loading -> city_progress_bar.visibility = View.VISIBLE
                        else -> city_progress_bar.visibility = View.GONE
                    }

                    when {
                        it.error -> city_error_text_view.visibility = View.VISIBLE
                        else -> city_error_text_view.visibility = View.GONE
                    }
                    if (cityForecastAdapter.itemCount == 0) {
                        cityForecastAdapter.add(it.forecastUiModels)
                    }
                }
            }
        })
    }

    companion object {
        fun newInstance(cityId: Int): CityForecastsFragment {
            val fragment = CityForecastsFragment()
            fragment.arguments = Bundle().also {
                it.putInt(CityForecastsActivity.CITY_ID_BUNDLE_KEY, cityId)
            }
            return fragment
        }
    }
}