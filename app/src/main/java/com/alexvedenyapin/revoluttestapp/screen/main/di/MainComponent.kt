package com.alexvedenyapin.revoluttestapp.screen.main.di

import com.alexvedenyapin.revoluttestapp.app.di.ActivityScope
import com.alexvedenyapin.revoluttestapp.screen.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(target: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainComponent
    }
}

interface MainComponentProvider {
    fun provideMainComponent(target: MainActivity): MainComponent
}