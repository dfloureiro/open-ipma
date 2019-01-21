package com.bskyb.domainpersistence.usecase

import com.bskyb.domainpersistence.repository.PersistenceRepository

class HandleLastKnownLocationUseCase(private val persistenceRepository: PersistenceRepository) {

    fun getLastKnownTerritoryId(): Int {
        return persistenceRepository.getInt(LAST_KNOWN_TERRITORY_KEY, LAST_KNOWN_TERRITORY_DEFAULT_VALUE)
    }

    fun setLastKnownTerritoryId(territoryId: Int) {
        persistenceRepository.putInt(LAST_KNOWN_TERRITORY_KEY, territoryId)
    }

    companion object {
        private const val LAST_KNOWN_TERRITORY_KEY = "LAST_KNOWN_TERRITORY_KEY"
        private const val LAST_KNOWN_TERRITORY_DEFAULT_VALUE = 0
    }
}