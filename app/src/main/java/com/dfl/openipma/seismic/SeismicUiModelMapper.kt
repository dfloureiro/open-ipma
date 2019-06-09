package com.dfl.openipma.seismic

import com.dfl.domainipma.model.SeismicInfo
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@Reusable
class SeismicUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.forLanguageTag("pt"))

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
        seismicTime.time = simpleDateFormat.parse(date)
        return seismicTime.timeInMillis
    }

    private fun getTimeString(date: String): String {
        val seismicTime = GregorianCalendar()
        seismicTime.time = simpleDateFormat.parse(date)
        return "${seismicTime.get(Calendar.DAY_OF_MONTH)}/" +
                "${seismicTime.get(Calendar.MONTH)}/" +
                "${seismicTime.get(Calendar.YEAR)} " +
                String.format("%02d\n", seismicTime.get(Calendar.HOUR_OF_DAY)).dropLast(1) +
                "h:${String.format("%02d\n", seismicTime.get(Calendar.MINUTE)).dropLast(1)}m"
    }
}