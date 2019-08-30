package com.dfl.openipma.home

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.dfl.domainanalytics.usecase.HandleOnSettingsChangeEvents
import com.dfl.domainipma.model.*
import com.dfl.domainipma.usecase.*
import com.dfl.domainpersistence.usecase.GetWeatherNotificationPreferencesUseCase
import com.dfl.domainpersistence.usecase.HandleFavouriteCitiesUseCase
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
    private val getWeatherNotificationPreferencesUseCase: GetWeatherNotificationPreferencesUseCase,
    private val handleFavouriteCitiesUseCase: HandleFavouriteCitiesUseCase
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
                val forecastUiModels =
                    homeForecastUiModelMapper.create(forecasts, windSpeeds, weatherTypes, cities)
                homeViewState.value = HomeViewState(
                    privacyPolicy = getWeatherNotificationPreferencesUseCase.getPrivacyPolicyDialogShowed().not(),
                    homeForecastUiModels = forecastUiModels
                )
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    fun checkForFavouriteUpdates() {
        // TODO
        /**
         * compare current viewModel favs with usecase favs
         * if they are the same, no changes needed
         * if they are different:
         *      if it's an add we need to notify the move position from original position to the favPosition
         *      if it's a remove we need to notify the move from the favPosition to the original
         *
         * the closest city is always the index 0
         */

        scope.launch {
            val currentForecasts = homeViewState.value!!.homeForecastUiModels
            val closestCity = currentForecasts.firstOrNull { it.isClosestCity }?.cityId.toString()
            val currentFavs =
                currentForecasts.filter { it.isFavourite }.map { it.cityId.toString() }
            val newFavs = handleFavouriteCitiesUseCase.getFavouriteCities()

            if (newFavs != null && currentFavs.containsAll(newFavs).not()) {

                val listX = mutableListOf<Pair<Int, Int>>()

                currentFavs
                    .filter { newFavs.contains(it).not() && it != closestCity }
                    .forEach { fav ->
                        currentForecasts.find { it.cityId.toString() == fav }?.also {
                            val currentPosition = currentForecasts.indexOf(it)
                            val newPosition = it.originalPosition
                            listX.add(Pair(currentPosition, newPosition))
                        }
                    }

                val favsToAdd =
                    newFavs.filter { currentFavs.contains(it).not() && it != closestCity }

                favsToAdd.forEach { fav ->
                    currentForecasts.find { it.cityId.toString() == fav }
                        ?.also {
                            val currentPosition = it.originalPosition
                            val newPosition = favsToAdd.indexOf(fav)
                            listX.add(Pair(currentPosition, newPosition))
                        }
                }

                //TODO pass the listX to the recycler view to move
            }

            val favouriteMoves = mutableListOf<Pair<Int, Int>>()
            val currentPosition = 0
            val newPosition = 1
            favouriteMoves.add(Pair(currentPosition, newPosition))
        }
    }

    fun setPrivacyPolicyPreferences(status: Boolean) {
        handleOnSettingsChangeEvents.setAnalyticsStatus(status)
        getWeatherNotificationPreferencesUseCase.setAnalyticsStatus(status)
        getWeatherNotificationPreferencesUseCase.setPrivacyPolicyDialogShowed(true)
        homeViewState.value = homeViewState.value?.copy(privacyPolicy = false)
    }

    private suspend fun loadForecastsForDay(closestCity: City?): List<Forecast> {
        return getForecastsForDayUseCase.buildUseCase(
            GetForecastsForDayUseCase.Params(
                Day.TODAY,
                closestCity
            )
        )
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