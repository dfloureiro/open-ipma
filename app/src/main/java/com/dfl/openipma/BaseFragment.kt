package com.dfl.openipma

import android.content.Context
import android.support.v4.app.Fragment
import com.dfl.openipma.di.Injector

open class BaseFragment : Fragment() {

    lateinit var injector: Injector

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injector = (activity?.application as IpmaApplication).injector
    }
}