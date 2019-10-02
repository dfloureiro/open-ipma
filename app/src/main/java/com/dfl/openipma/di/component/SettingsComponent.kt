package com.dfl.openipma.di.component

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.settings.SettingsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface SettingsComponent {

    fun inject(settingsFragment: SettingsFragment)
}
