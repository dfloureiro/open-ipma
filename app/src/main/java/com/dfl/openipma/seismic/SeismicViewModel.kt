package com.dfl.openipma.seismic

import android.arch.lifecycle.MutableLiveData
import com.dfl.domainipma.model.SeismicInfo
import com.dfl.domainipma.usecase.GetSeismicInfoForAreaIdUseCase
import com.dfl.openipma.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeismicViewModel @Inject constructor(
    private val getSeismicInfoForAreaIdUseCase: GetSeismicInfoForAreaIdUseCase,
    private val seismicUiModelMapper: SeismicUiModelMapper
) : BaseViewModel() {

    val seismicState = MutableLiveData<SeismicState>()

    fun loadData() {
        seismicState.value = SeismicState(loading = true)
        scope.launch {
            try {
                val seismicInfoAzores = loadSeismicInfo(azoresAreaId)
                val seismicInfoContinentAndMadeira = loadSeismicInfo(continentAndMadeiraAreaId)
                val uiModels = seismicUiModelMapper.map(seismicInfoAzores + seismicInfoContinentAndMadeira)
                seismicState.value = SeismicState(seismicUiModels = uiModels)
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
        val seismicUiModels: List<SeismicUiModel> = listOf()
    )

    companion object {
        private const val azoresAreaId = 3
        private const val continentAndMadeiraAreaId = 7
    }
}