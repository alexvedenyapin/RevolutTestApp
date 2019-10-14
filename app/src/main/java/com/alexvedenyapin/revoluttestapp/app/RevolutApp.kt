package com.alexvedenyapin.revoluttestapp.app

import android.app.Application
import com.alexvedenyapin.revoluttestapp.app.di.AppModule
import com.alexvedenyapin.revoluttestapp.app.di.DaggerAppComponent

/**
 * Created by Alex Vedeniapin on 14.10.2019
 */
class RevolutApp : Application() {

    private val component by lazy {
        DaggerAppComponent
            .factory()
            .create(
                appModule = AppModule(),
                app = this
            )
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}