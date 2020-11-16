package com.mandiri.fahri.di.component

import com.mandiri.fahri.MovieApps
import com.mandiri.fahri.di.builder.ActivityBuilder
import com.mandiri.fahri.di.module.ApplicationModule
import com.mandiri.fahri.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    ActivityBuilder::class
])


interface ApplicationComponent  : AndroidInjector<MovieApps>