package com.dfl.openipma.seismic

import com.dfl.common.dateDivider
import com.dfl.common.dateFormatLanguageCode
import com.dfl.common.dateWithTimeFormat
import com.dfl.common.hoursFormat
import com.dfl.common.hoursSuffix
import com.dfl.common.minutesSuffix
import com.dfl.common.timeDivider
import com.dfl.domainipma.model.SeismicInfo
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import javax.inject.Inject

@Reusable
class SeismicUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    private val simpleDateFormat =
        SimpleDateFormat(dateWithTimeFormat, Locale.forLanguageTag(dateFormatLanguageCode))

    fun map(seismicInfos: List<SeismicInfo>): List<SeismicUiModel> {
        return seismicInfos
            .sortedByDescending { getTimeInMillis(it.time) }
            .map {
                SeismicUiModel(
                    it.magnitude,
                    it.region,
                    getTimeString(it.time),
                    it.latitude,
                    it.longitude,
                    it.sensed
                )
            }
    }

    private fun getTimeInMillis(date: String): Long {
        val seismicTime = GregorianCalendar()
        simpleDateFormat.parse(date)?.also { seismicTime.time = it }
        return seismicTime.timeInMillis
    }

    private fun getTimeString(date: String): String {
        val seismicTime = GregorianCalendar()
        simpleDateFormat.parse(date)?.also { seismicTime.time = it }
        return "${seismicTime.get(Calendar.DAY_OF_MONTH)}$dateDivider" +
            "${seismicTime.get(Calendar.MONTH)}$dateDivider" +
            "${seismicTime.get(Calendar.YEAR)} " +
            String.format(hoursFormat, seismicTime.get(Calendar.HOUR_OF_DAY)).dropLast(1) +
            "$hoursSuffix$timeDivider${String.format(
                hoursFormat,
                seismicTime.get(Calendar.MINUTE)
            ).dropLast(1)}$minutesSuffix"
    }
}
