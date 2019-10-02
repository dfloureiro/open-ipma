package com.dfl.openipma.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {

    private val viewModelJob = Job()
    val scope = CoroutineScope(Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
