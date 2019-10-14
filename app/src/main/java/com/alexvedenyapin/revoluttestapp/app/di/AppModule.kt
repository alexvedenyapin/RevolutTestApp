package com.alexvedenyapin.revoluttestapp.app.di

import android.content.Context
import com.alexvedenyapin.revoluttestapp.app.RevolutApp
import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by Alex Vedeniapin on 14.10.2019
 */

@Module
class AppModule {
    @Provides
    @Singleton
    @AppContext
    fun appContext(app: RevolutApp): Context = app

    @Provides
    @Singleton
    fun workers() = RxWorkers(Schedulers.io(), AndroidSchedulers.mainThread())
}