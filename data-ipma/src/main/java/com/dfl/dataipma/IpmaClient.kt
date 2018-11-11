package com.dfl.dataipma

import com.dfl.dataipma.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface IpmaClient {

    @GET("open-data/forecast/meteorology/cities/daily/{globalIdLocal}.json")
    fun getForecastsForCity(@Path("globalIdLocal") localGlobalId: Int): Deferred<ForecastsCityDto>

    @GET("open-data/forecast/meteorology/cities/daily/hp-daily-forecast-day{idDay}.json")
    fun getForecastsForDay(@Path("idDay") dayId: Int): Deferred<ForecastsDayDto>

    @GET("open-data/distrits-islands.json")
    fun getLocalGlobalIds(): Deferred<GlobalIdsDto>

    @GET("open-data/weather-type-classe.json")
    fun getWeatherTypes(): Deferred<WeatherTypesDto>

    @GET("open-data/wind-speed-daily-classe.json")
    fun getWindSpeeds(): Deferred<WindSpeedsDto>
}