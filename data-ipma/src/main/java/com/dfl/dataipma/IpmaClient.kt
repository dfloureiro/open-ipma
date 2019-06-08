package com.dfl.dataipma

import com.dfl.dataipma.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface IpmaClient {

    @GET("open-data/forecast/meteorology/cities/daily/{globalIdLocal}.json")
    suspend fun getForecastsForCity(@Path("globalIdLocal") localGlobalId: Int): ForecastsCityDto

    @GET("open-data/forecast/meteorology/cities/daily/hp-daily-forecast-day{idDay}.json")
    suspend fun getForecastsForDay(@Path("idDay") dayId: Int): ForecastsDayDto

    @GET("open-data/distrits-islands.json")
    suspend fun getLocalGlobalIds(): GlobalIdsDto

    @GET("open-data/weather-type-classe.json")
    suspend fun getWeatherTypes(): WeatherTypesDto

    @GET("open-data/wind-speed-daily-classe.json")
    suspend fun getWindSpeeds(): WindSpeedsDto

    @GET("open-data/observation/seismic/{idArea}.json")
    suspend fun getSeismicForArea(@Path("idArea") areaId: Int): SeismicInfosDto
}