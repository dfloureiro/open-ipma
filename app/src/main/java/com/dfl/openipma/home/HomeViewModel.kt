package com.dfl.openipma.home

import android.arch.lifecycle.MutableLiveData
import android.location.Location
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
import com.dfl.domainipma.model.*
import com.dfl.domainipma.usecase.*
import com.dfl.openipma.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getForecastsForDayUseCase: GetForecastsForDayUseCase,
    private val getWindSpeedsUseCase: GetWindSpeedsUseCase,
    private val getWeatherTypesUseCase: GetWeatherTypesUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getClosestCityUseCase: GetClosestCityUseCase,
    private val handleLastKnownLocationUseCase: HandleLastKnownLocationUseCase,
    private val homeForecastUiModelMapper: HomeForecastUiModelMapper
) : BaseViewModel() {

    val homeViewState = MutableLiveData<HomeViewState>()

    fun loadData(currentLocation: Location?) {
        homeViewState.value = HomeViewState(loading = true)
        scope.launch {
            try {
                val cities = loadCities()
                val closestCity = getClosestCity(cities, currentLocation)
                val forecasts = loadForecastsForDay(closestCity)
                val windSpeeds = loadWindSpeeds()
                val weatherTypes = loadWeatherTypes()
                val forecastUiModels = homeForecastUiModelMapper.create(forecasts, windSpeeds, weatherTypes, cities)
                homeViewState.value =
                    HomeViewState(homeForecastUiModels = forecastUiModels)
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    private suspend fun loadForecastsForDay(closestCity: City?): List<Forecast> {
        return getForecastsForDayUseCase.buildUseCase(GetForecastsForDayUseCase.Params(Day.TODAY, closestCity))
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

    private suspend fun getClosestCity(cities: List<City>, location: Location?): City? {
        return when {
            location != null -> {
                getClosestCityUseCase.buildUseCase(
                    GetClosestCityUseCase.Params(
                        cities,
                        location.longitude,
                        location.latitude
                    )
                ).also { handleLastKnownLocationUseCase.setLastKnownTerritoryId(it.id) }
            }
            else -> cities.find { it.id == handleLastKnownLocationUseCase.getLastKnownTerritoryId() }
        }
    }

    data class HomeViewState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val homeForecastUiModels: List<HomeForecastUiModel> = listOf()
    )
}