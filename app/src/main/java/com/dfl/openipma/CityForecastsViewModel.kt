package com.dfl.openipma

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.CityForecast
import com.dfl.domainipma.model.WeatherType
import com.dfl.domainipma.model.WindSpeed
import com.dfl.domainipma.usecase.GetForecastsForCityUseCase
import com.dfl.domainipma.usecase.GetWeatherTypesUseCase
import com.dfl.domainipma.usecase.GetWindSpeedsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityForecastsViewModel @Inject constructor(
    private val getForecastsForCityUseCase: GetForecastsForCityUseCase,
    private val getWindSpeedsUseCase: GetWindSpeedsUseCase,
    private val getWeatherTypesUseCase: GetWeatherTypesUseCase,
    private val cityForecastsUiModelMapper: CityForecastUiModelMapper
) :
    BaseViewModel() {

    val cityForecastsState = MutableLiveData<CityForecastsState>()

    fun loadData(cityId: Int) {
        scope.launch {
            try {
                val cityForecasts = loadCityForecasts(cityId)
                val windSpeeds = loadWindSpeeds()
                val weatherTypes = loadWeatherTypes()
                val uiModels = cityForecastsUiModelMapper.map(cityForecasts, windSpeeds, weatherTypes)
                val todayUiModel = uiModels.find { it.isToday }
                if (todayUiModel != null) {
                    cityForecastsState.value =
                        CityForecastsState(
                            todayUiModel = todayUiModel,
                            forecastUiModels = uiModels.toMutableList().also { it.remove(todayUiModel) }
                        )
                } else {
                    throw IllegalArgumentException("Could not find valid today's forecast for cityId $cityId")
                }
            } catch (e: Exception) {
                cityForecastsState.value = CityForecastsState(error = true)
            }
        }
    }

    private suspend fun loadCityForecasts(cityId: Int): List<CityForecast> {
        return getForecastsForCityUseCase.buildUseCase(GetForecastsForCityUseCase.Params(cityId))
    }

    private suspend fun loadWindSpeeds(): List<WindSpeed> {
        return getWindSpeedsUseCase.buildUseCase()
    }

    private suspend fun loadWeatherTypes(): List<WeatherType> {
        return getWeatherTypesUseCase.buildUseCase()
    }

    data class CityForecastsState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val todayUiModel: CityForecastUiModel? = null,
        val forecastUiModels: List<CityForecastUiModel> = listOf()
    )
}


