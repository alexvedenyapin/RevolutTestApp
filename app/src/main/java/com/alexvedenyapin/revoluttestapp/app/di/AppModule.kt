package com.alexvedenyapin.revoluttestapp.app.di

import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import com.alexvedenyapin.revoluttestapp.model.CurrencyRateMapper
import com.alexvedenyapin.revoluttestapp.network.ApiService
import com.alexvedenyapin.revoluttestapp.screen.main.data.CurrenciesRatesRepository
import com.alexvedenyapin.revoluttestapp.screen.main.data.CurrenciesRatesRestRepository
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun workers() = RxWorkers(Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    @Singleton
    fun repository(
        api: ApiService,
        currencyRateMapper: CurrencyRateMapper
    ): CurrenciesRatesRepository = CurrenciesRatesRestRepository(api, currencyRateMapper)

    @Provides
    @Singleton
    fun apiService() = ApiService.create()

    @Provides
    fun currencyRateMapper() = CurrencyRateMapper()
}