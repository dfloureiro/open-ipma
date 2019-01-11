package com.dfl.openipma

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private val homeForecastAdapter: HomeForecastsAdapter = HomeForecastsAdapter(this)

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home_toolbar_title)
        viewModel = ViewModelProviders.of(this, viewModeFactory).get(HomeViewModel::class.java)
        when {
            viewModel.homeViewState.value == null -> viewModel.loadData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_recycler_view.apply {
            adapter = homeForecastAdapter
        }
        viewModel.homeViewState.observe(viewLifecycleOwner, Observer<HomeViewModel.HomeViewState> {
            when {
                it != null -> {
                    when {
                        it.loading -> home_progress_bar.visibility = View.VISIBLE
                        else -> home_progress_bar.visibility = View.GONE
                    }

                    when {
                        it.error -> home_error_text_view.visibility = View.VISIBLE
                        else -> home_error_text_view.visibility = View.GONE
                    }
                    if (homeForecastAdapter.itemCount == 0) {
                        homeForecastAdapter.add(it.forecastUiModels)
                    }
                }
            }
        })
    }

    fun loadForecastsForCity(cityId: Int, cityName: String) {
        val intent = Intent(activity, CityForecastsActivity::class.java)
        intent.putExtra(CityForecastsActivity.CITY_ID_BUNDLE_KEY, cityId)
        intent.putExtra(CityForecastsActivity.CITY_NAME_BUNDLE_KEY, cityName)
        (activity as MainActivity).startActivity(intent)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}