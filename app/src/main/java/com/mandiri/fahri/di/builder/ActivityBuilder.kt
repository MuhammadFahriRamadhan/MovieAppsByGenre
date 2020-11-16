package com.mandiri.fahri.di.builder

import com.mandiri.fahri.di.module.HomeModule
import com.mandiri.fahri.di.scope.Presentation
import com.mandiri.fahri.presentation.DetailMovie
import com.mandiri.fahri.presentation.HomeMovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Presentation
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity() : HomeMovieActivity
    @Presentation
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeDetailActivity() : DetailMovie

}