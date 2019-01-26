package com.dfl.openipma

import android.content.Context
import android.support.v4.app.Fragment
import com.dfl.openipma.di.Injector

open class BaseFragment : Fragment() {

    lateinit var injector: Injector

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity = activity
        when {
            activity != null -> injector = (activity.application as IpmaApplication).injector
            else -> throw KotlinNullPointerException("can't instantiate the injector without a valid activity")
        }
    }
}