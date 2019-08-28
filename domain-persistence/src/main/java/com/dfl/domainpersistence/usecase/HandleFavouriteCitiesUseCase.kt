package com.dfl.domainpersistence.usecase

import com.dfl.domainpersistence.FAVOURITE_CITIES_KEY
import com.dfl.domainpersistence.repository.PersistenceRepository

class HandleFavouriteCitiesUseCase(private val persistenceRepository: PersistenceRepository) {

    fun addFavourite(cityId: String) {
        val currentFavouriteCities = getFavouriteCities()?.toMutableSet()
        currentFavouriteCities?.also {
            it.add(cityId)
            persistenceRepository.putStringSet(FAVOURITE_CITIES_KEY, it)
        }
    }

    fun removeFavourite(cityId: String) {
        val currentFavouriteCities = getFavouriteCities()?.toMutableSet()
        currentFavouriteCities?.also {
            it.remove(cityId)
            persistenceRepository.putStringSet(FAVOURITE_CITIES_KEY, it)
        }
    }

    fun getFavouriteCities(): MutableSet<String>? {
        return persistenceRepository.getStringSet(FAVOURITE_CITIES_KEY, setOf())
    }
}