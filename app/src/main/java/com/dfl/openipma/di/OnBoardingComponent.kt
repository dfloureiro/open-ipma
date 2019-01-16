package com.dfl.openipma.di

import com.dfl.openipma.OnBoardingActivity
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface OnBoardingComponent {

    fun inject(onBoardingActivity: OnBoardingActivity)
}