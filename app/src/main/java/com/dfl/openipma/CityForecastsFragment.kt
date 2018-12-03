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

    private lateinit var viewModel: CityForecastsViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cityId = arguments?.getInt(CITY_ID_BUNDLE_KEY)
        val cityName = arguments?.getString(CITY_NAME_BUNDLE_KEY)

        if (cityId != null && cityName != null) {
            viewModel = ViewModelProviders.of(this, viewModeFactory).get(CityForecastsViewModel::class.java)
            when {
                viewModel.cityForecastsState.value == null -> {
                    viewModel.loadData(cityId, cityName)
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
                }
            }
        })
    }

    companion object {
        const val CITY_ID_BUNDLE_KEY = "CITY_ID_BUNDLE_KEY"
        const val CITY_NAME_BUNDLE_KEY = "CITY_NAME_BUNDLE_KEY"

        fun newInstance(cityId: Int, cityName: String): CityForecastsFragment {
            val fragment = CityForecastsFragment()
            fragment.arguments = Bundle().also {
                it.putInt(CITY_ID_BUNDLE_KEY, cityId)
                it.putString(CITY_NAME_BUNDLE_KEY, cityName)
            }
            return fragment
        }
    }
}