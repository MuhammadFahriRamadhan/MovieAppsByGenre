package com.mandiri.fahri.di.module

import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideJobExecutor() :JobExecutor = JobExecutor()

    @Provides
    @Singleton
    fun providesUIThread() : UIThread = UIThread()


}