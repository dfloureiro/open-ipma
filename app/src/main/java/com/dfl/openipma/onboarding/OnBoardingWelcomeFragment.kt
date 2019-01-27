package com.dfl.openipma.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R
import com.dfl.openipma.base.BaseFragment

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