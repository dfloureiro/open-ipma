package com.dfl.dataipma

import android.text.format.DateUtils
import com.dfl.common.dateFormat
import com.dfl.common.dateFormatLanguageCode
import com.dfl.domainipma.repository.DateRepository
import java.text.SimpleDateFormat
import java.util.GregorianCalendar
import java.util.Locale

class DateRepositoryImpl : DateRepository {

    override fun isToday(date: String): Boolean {
        return DateUtils.isToday(getTimeInMillis(date))
    }

    override fun getTimeInMillis(date: String): Long {
        val forecastDate = GregorianCalendar()
        simpleDateFormat.parse(date)?.also { forecastDate.time = it }
        return forecastDate.timeInMillis
    }

    companion object {
        private val simpleDateFormat =
            SimpleDateFormat(dateFormat, Locale.forLanguageTag(dateFormatLanguageCode))
    }
}
