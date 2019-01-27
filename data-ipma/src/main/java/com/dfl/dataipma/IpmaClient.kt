package com.dfl.dataipma

import com.dfl.dataipma.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface IpmaClient {

    @GET("open-data/forecast/meteorology/cities/daily/{globalIdLocal}.json")
    fun getForecastsForCityAsync(@Path("globalIdLocal") localGlobalId: Int): Deferred<ForecastsCityDto>

    @GET("open-data/forecast/meteorology/cities/daily/hp-daily-forecast-day{idDay}.json")
    fun getForecastsForDayAsync(@Path("idDay") dayId: Int): Deferred<ForecastsDayDto>

    @GET("open-data/distrits-islands.json")
    fun getLocalGlobalIdsAsync(): Deferred<GlobalIdsDto>

    @GET("open-data/weather-type-classe.json")
    fun getWeatherTypesAsync(): Deferred<WeatherTypesDto>

    @GET("open-data/wind-speed-daily-classe.json")
    fun getWindSpeedsAsync(): Deferred<WindSpeedsDto>
}