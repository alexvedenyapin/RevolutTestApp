package com.alexvedenyapin.revoluttestapp.app

import android.app.Application
import com.alexvedenyapin.revoluttestapp.app.di.AppModule
import com.alexvedenyapin.revoluttestapp.app.di.DaggerAppComponent
import com.alexvedenyapin.revoluttestapp.screen.main.MainActivity
import com.alexvedenyapin.revoluttestapp.screen.main.di.MainComponentProvider

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class RevolutApp : Application(), MainComponentProvider {
    private val component by lazy {
        DaggerAppComponent
            .factory()
            .create(
                appModule = AppModule()
            )
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun provideMainComponent(target: MainActivity) =
        component.plusMain()
            .create(target)
}