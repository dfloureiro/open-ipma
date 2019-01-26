package com.dfl.openipma.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Named("application")
    @Singleton
    @Provides
    fun applicationContext(): Context {
        return context
    }
}