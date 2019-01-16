package com.dfl.openipma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class OnBoardingWelcomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.on_boarding_welcome_fragment, container, false)
    }

    companion object {
        fun newInstance(): OnBoardingWelcomeFragment {
            return OnBoardingWelcomeFragment()
        }
    }
}