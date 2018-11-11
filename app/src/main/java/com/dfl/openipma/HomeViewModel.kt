package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Day
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetForecastsForDayUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val getForecastsForCityUseCase: GetForecastsForCityUseCase,
        private val getForecastsForDayUseCase: GetForecastsForDayUseCase,
        private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel() {

    val homeViewState = MutableLiveData<HomeViewState>()

    fun loadData() {
        scope.launch {
            //TODO mapping into UImodels
            try {
                homeViewState.value = HomeViewState(forecastsCity = loadForecastsForCity(), forecastsDay = loadForecastsForDay(), cities = loadCities())
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    private suspend fun loadForecastsForCity(): List<Forecast> {
        return getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(1110600))
    }

    private suspend fun loadForecastsForDay(): List<Forecast> {
        return getForecastsForDayUseCase.buildUseCase(GetForecastsForDayUseCase.Params(Day.TODAY))
    }

    private suspend fun loadCities(): List<City> {
        return getCitiesUseCase.buildUseCase()
    }

    data class HomeViewState(val loading: Boolean = false,
                             val error: Boolean = false,
                             val forecastsCity: List<Forecast> = listOf(),
                             val forecastsDay: List<Forecast> = listOf(),
                             val cities: List<City> = listOf())
}