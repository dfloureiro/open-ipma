package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.Forecast
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityForecastsViewModel @Inject constructor(private val getForecastsForCityUseCase: GetForecastsForCityUseCase) :
    BaseViewModel() {

    val cityForecastsState = MutableLiveData<CityForecastsState>()

    fun loadData(cityId: Int) {
        scope.launch {
            try {
                val forecasts = loadCityForecasts(cityId)
                //todo creator to ui model
                cityForecastsState.value = CityForecastsState()
            } catch (e: Exception) {
                cityForecastsState.value = CityForecastsState(error = true)
            }
        }
    }

    private suspend fun loadCityForecasts(cityId: Int): List<Forecast> {
        return getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(cityId))
    }

    data class CityForecastsState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val forecastUiModels: List<ForecastUiModel> = listOf()
    )
}


