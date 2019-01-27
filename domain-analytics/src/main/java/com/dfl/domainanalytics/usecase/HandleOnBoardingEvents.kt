package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnBoardingEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logOnBoardingBegin() {
        analyticsRepository.logEvent("on_boarding_begin", mapOf())
    }

    fun logOnBoardingComplete() {
        analyticsRepository.logEvent("on_boarding_complete", mapOf())
    }
}