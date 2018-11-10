package com.dfl.openipma

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.android.Main

open class BaseViewModel : ViewModel() {

    private val viewModelJob = Job()
    val scope = CoroutineScope(kotlinx.coroutines.Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}