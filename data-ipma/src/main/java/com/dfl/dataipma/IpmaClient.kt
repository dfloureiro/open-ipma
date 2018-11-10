package com.dfl.dataipma

import com.dfl.dataipma.model.ForecastsDto
import com.dfl.dataipma.model.GlobalIdsDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface IpmaClient {

    @GET("open-data/forecast/meteorology/cities/daily/{globalIdLocal}.json")
    fun getForecast(@Path("globalIdLocal") localGlobalId: Int): Deferred<ForecastsDto>

    @GET("open-data/distrits-islands.json")
    fun getLocalGlobalIds(): Deferred<GlobalIdsDto>
}