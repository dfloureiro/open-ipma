package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import android.location.Location
import com.dfl.domainipma.model.*
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForDayUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getForecastsForDayUseCase: GetForecastsForDayUseCase,
    private val getWindSpeedsUseCase: GetWindSpeedsUseCase,
    private val getWeatherTypesUseCase: GetWeatherTypesUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val forecastUiModelCreator: ForecastUiModelCreator
) : BaseViewModel() {

    val homeViewState = MutableLiveData<HomeViewState>()

    fun loadData(currentLocation: Location?) {
        scope.launch {
            try {
                val forecasts = loadForecastsForDay()
                val windSpeeds = loadWindSpeeds()
                val weatherTypes = loadWeatherTypes()
                val cities = loadCities()
                val forecastUiModels = forecastUiModelCreator.create(forecasts, windSpeeds, weatherTypes, cities)
                homeViewState.value = HomeViewState(forecastUiModels = forecastUiModels)
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    private suspend fun loadForecastsForDay(): List<Forecast> {
        return getForecastsForDayUseCase.buildUseCase(GetForecastsForDayUseCase.Params(Day.TODAY))
    }

    private suspend fun loadWindSpeeds(): List<WindSpeed> {
        return getWindSpeedsUseCase.buildUseCase()
    }

    private suspend fun loadWeatherTypes(): List<WeatherType> {
        return getWeatherTypesUseCase.buildUseCase()
    }

    private suspend fun loadCities(): List<City> {
        return getCitiesUseCase.buildUseCase()
    }

    data class HomeViewState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val forecastUiModels: List<ForecastUiModel> = listOf()
    )
}