package com.mandiri.fahri


import com.mandiri.fahri.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MovieApps : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.create().apply {inject(this@MovieApps)}
    }


}
