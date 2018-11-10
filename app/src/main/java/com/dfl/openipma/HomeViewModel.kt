package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getForecastsForCityUseCase: GetForecastsForCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel() {

    val forecasts = MutableLiveData<List<Forecast>>()
    val cities = MutableLiveData<List<City>>()

    init {
        loadData()
    }

    private fun loadData() {
        loadForecasts()
        loadCities()
    }

    private fun loadForecasts() {
        scope.launch {
            forecasts.value = getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(1110600))
            //TODO mapping into UImodels
        }
    }

    private fun loadCities() {
        scope.launch {
            cities.value = getCitiesUseCase.buildUseCase()
            //TODO mapping into UImodels
        }
    }
}