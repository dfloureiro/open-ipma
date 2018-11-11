package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.City
import com.dfl.domainipma.model.Day
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.usecase.GetCitiesUseCase
import com.dfl.domainipma.usecase.GetForecastsForDayUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getForecastsForDayUseCase: GetForecastsForDayUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val forecastUiModelCreator: ForecastUiModelCreator
) : BaseViewModel() {

    val homeViewState = MutableLiveData<HomeViewState>()

    fun loadData() {
        scope.launch {
            try {
                val forecasts = loadForecastsForDay()
                val cities = loadCities()
                val forecastUiModels = forecastUiModelCreator.create(forecasts, cities)
                homeViewState.value = HomeViewState(forecastUiModels = forecastUiModels)
            } catch (e: Exception) {
                homeViewState.value = HomeViewState(error = true)
            }
        }
    }

    private suspend fun loadForecastsForDay(): List<Forecast> {
        return getForecastsForDayUseCase.buildUseCase(GetForecastsForDayUseCase.Params(Day.TODAY))
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