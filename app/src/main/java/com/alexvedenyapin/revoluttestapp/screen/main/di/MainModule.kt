package com.alexvedenyapin.revoluttestapp.screen.main.di

import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import com.alexvedenyapin.revoluttestapp.app.di.ActivityScope
import com.alexvedenyapin.revoluttestapp.screen.main.MainViewModelFactory
import com.alexvedenyapin.revoluttestapp.screen.main.data.CurrenciesRatesRepository
import com.alexvedenyapin.revoluttestapp.screen.main.domain.MainInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
@Module
class MainModule {
    @Provides
    @ActivityScope
    fun interactor(currenciesRatesRepository: CurrenciesRatesRepository, workers: RxWorkers) =
        MainInteractor(currenciesRatesRepository, workers)

    @Provides
    @ActivityScope
    fun viewModelFactory(interactor: MainInteractor) =
        MainViewModelFactory(interactor)
}