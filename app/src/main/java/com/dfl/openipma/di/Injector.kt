package com.dfl.openipma.di

import com.dfl.openipma.HomeFragment

class Injector {

    fun inject(homeFragment: HomeFragment) {
        DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .ipmaModule(IpmaModule())
            .build()
            .inject(homeFragment)
    }
}