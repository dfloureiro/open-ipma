package com.bskyb.domainpersistence.usecase

import com.bskyb.domainpersistence.repository.PersistenceRepository

class HandleFirstLaunchUseCase(private val persistenceRepository: PersistenceRepository) {

    fun isFirstLaunch(): Boolean {
        return persistenceRepository.getBoolean(FIRST_LAUNCH_KEY, FIRST_LAUNCH_DEFAULT_VALUE)
    }

    fun setHasNotFirstLaunch() {
        persistenceRepository.putBoolean(FIRST_LAUNCH_KEY, false)
    }

    companion object {
        const val FIRST_LAUNCH_KEY = "FIRST_LAUNCH_KEY"
        const val FIRST_LAUNCH_DEFAULT_VALUE = true
    }
}