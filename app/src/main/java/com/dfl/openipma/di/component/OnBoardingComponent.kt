package com.dfl.openipma.di.component

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.onboarding.OnBoardingActivity
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface OnBoardingComponent {

    fun inject(onBoardingActivity: OnBoardingActivity)
}
