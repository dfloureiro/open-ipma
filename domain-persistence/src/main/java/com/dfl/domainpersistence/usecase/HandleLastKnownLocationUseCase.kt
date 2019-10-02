package com.dfl.domainpersistence.usecase

import com.dfl.domainpersistence.LAST_KNOWN_TERRITORY_DEFAULT_VALUE
import com.dfl.domainpersistence.LAST_KNOWN_TERRITORY_KEY
import com.dfl.domainpersistence.repository.PersistenceRepository

class HandleLastKnownLocationUseCase(private val persistenceRepository: PersistenceRepository) {

    fun getLastKnownTerritoryId(): Int {
        return persistenceRepository.getInt(
            LAST_KNOWN_TERRITORY_KEY,
            LAST_KNOWN_TERRITORY_DEFAULT_VALUE
        )
    }

    fun setLastKnownTerritoryId(territoryId: Int) {
        persistenceRepository.putInt(LAST_KNOWN_TERRITORY_KEY, territoryId)
    }

    fun wasLastKnownTerritoryIdSet(): Boolean {
        return getLastKnownTerritoryId() != LAST_KNOWN_TERRITORY_DEFAULT_VALUE
    }
}
