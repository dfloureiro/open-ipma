package com.dfl.openipma.home

import androidx.lifecycle.MutableLiveData
import android.location.Location
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import com.dfl.domainipma.model.*
import com.dfl.domainipma.usecase.*
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.domainpersistence.usecase.HandleLastKnownLocationUseCase
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
    private val homeForecastUiModelMapper: HomeForecastUiModelMapper,
    private val handleOnSettingsChangeEvents: HandleOnSettingsChangeEvents,
    private val getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase
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
                homeViewState.value = HomeViewState(
                    privacyPolicy = getWeatherNotificationPreferencesUseCase.getPrivacyPolicyDialogShowed().not(),
                    homeForecastUiModels = forecastUiModels
                )
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    fun setPrivacyPolicyPreferences(status: Boolean) {
        handleOnSettingsChangeEvents.setAnalyticsStatus(status)
        getWeatherNotificationPreferencesUseCase.setAnalyticsStatus(status)
        getWeatherNotificationPreferencesUseCase.setPrivacyPolicyDialogShowed(true)
        homeViewState.value = homeViewState.value?.copy(privacyPolicy = false)
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
        val privacyPolicy: Boolean = false,
        val loading: Boolean = false,
        val error: Boolean = false,
        val homeForecastUiModels: List<HomeForecastUiModel> = listOf()
    )
}