package com.alexvedenyapin.revoluttestapp.app.di

import com.alexvedenyapin.revoluttestapp.app.RevolutApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedeniapin on 14.10.2019
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: RevolutApp)

    @Component.Factory
    interface Factory {
        fun create(
            appModule: AppModule,
            @BindsInstance app: RevolutApp
        ): AppComponent
    }
}