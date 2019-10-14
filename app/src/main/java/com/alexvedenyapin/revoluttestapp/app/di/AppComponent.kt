package com.alexvedenyapin.revoluttestapp.app.di

import com.alexvedenyapin.revoluttestapp.app.RevolutApp
import com.alexvedenyapin.revoluttestapp.screen.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: RevolutApp)

    fun plusMain(): MainComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            appModule: AppModule
        ): AppComponent
    }
}