package com.dfl.openipma.seismic

import com.dfl.domainipma.model.SeismicInfo
import com.dfl.openipma.base.BaseUiModelMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SeismicUiModelMapper @Inject constructor() : BaseUiModelMapper() {

    fun map(seismicInfos: List<SeismicInfo>): List<SeismicUiModel> {
        return seismicInfos.map {
            SeismicUiModel(
                it.latitude,
                it.longitude,
                it.depth,
                it.magnitude,
                it.sensed
            )
        }
    }

}