package com.dfl.openipma.seismic

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.SeismicInfo
import com.dfl.domainipma.usecase.GetSeismicInfoForAreaIdUseCase
import com.dfl.openipma.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeismicViewModel @Inject constructor(
    private val getSeismicInfoForAreaIdUseCase: GetSeismicInfoForAreaIdUseCase
) : BaseViewModel() {

    val seismicState = MutableLiveData<SeismicState>()

    fun loadData() {
        seismicState.value = SeismicState(loading = true)
        scope.launch {
            try {
                val seismicInfoAzores = loadSeismicInfo(3)
                val seismicInfoContinentAndMadeira = loadSeismicInfo(7)
                seismicState.value = SeismicState(seismicUiModels = seismicInfoAzores + seismicInfoContinentAndMadeira)
            } catch (e: Exception) {
                seismicState.value = SeismicState(error = true)
            }
        }
    }

    private suspend fun loadSeismicInfo(areaId: Int): List<SeismicInfo> {
        return getSeismicInfoForAreaIdUseCase.buildUseCase(GetSeismicInfoForAreaIdUseCase.Params(areaId))
    }

    data class SeismicState(
        val loading: Boolean = false,
        val error: Boolean = false,
        val seismicUiModels: List<SeismicInfo> = listOf()
    )
}