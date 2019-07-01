package com.dfl.domainanalytics.usecase

import com.dfl.domainanalytics.ON_BOARDING_BEGIN_KEY
import com.dfl.domainanalytics.ON_BOARDING_COMPLETE_KEY
import com.dfl.domainanalytics.repository.AnalyticsRepository

class HandleOnBoardingEvents(private val analyticsRepository: AnalyticsRepository) {

    fun logOnBoardingBegin() {
        analyticsRepository.logEvent(ON_BOARDING_BEGIN_KEY, mapOf())
    }

    fun logOnBoardingComplete() {
        analyticsRepository.logEvent(ON_BOARDING_COMPLETE_KEY, mapOf())
    }
}