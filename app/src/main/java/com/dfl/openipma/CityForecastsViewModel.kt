package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityForecastsViewModel @Inject constructor(
    private val getForecastsForCityUseCase: GetForecastsForCityUseCase,
    private val cityForecastsUiModelMapper: CityForecastUiModelMapper
) :
    BaseViewModel() {

    val cityForecastsState = MutableLiveData<CityForecastsState>()

    fun loadData(cityId: Int) {
        scope.launch {
            try {
                val uiModels = cityForecastsUiModelMapper.map(loadCityForecasts(cityId))
                cityForecastsState.value = CityForecastsState(forecastUiModels = uiModels)
            } catch (e: Exception) {
                cityForecastsState.value = CityForecastsState(error = true)
            }
        }
    }

    private suspend fun loadCityForecasts(cityId: Int): List<CityForecast> {
        return getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(cityId))
    }

    data class CityForecastsState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val forecastUiModels: List<CityForecastUiModel> = listOf()
    )
}


