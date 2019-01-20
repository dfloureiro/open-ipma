package com.dfl.dataipma

import android.location.Location
import com.dfl.domainipma.model.City
import com.dfl.domainipma.repository.LocationRepository

class LocationRepositoryImpl : LocationRepository {

    override suspend fun getClosestCity(cities: List<City>, longitude: Double, latitude: Double): City {
        var closestDistance: Float = -1F
        var closestCity: City = cities.first()
        for (city in cities) {
            val distance = FloatArray(1)
            Location.distanceBetween(
                latitude,
                longitude,
                city.latitude,
                city.longitude,
                distance
            )
            if (closestDistance == -1F || closestDistance > distance[0]) {
                closestDistance = distance[0]
                closestCity = city
            }
        }
        return closestCity
    }
}