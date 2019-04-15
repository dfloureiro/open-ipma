package com.dfl.openipma.di.component

import com.dfl.openipma.di.scope.FragmentScope
import com.dfl.openipma.service.ServiceStarterJobService
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface JobServiceComponent {

    fun inject(serviceStarterJobService: ServiceStarterJobService)
}