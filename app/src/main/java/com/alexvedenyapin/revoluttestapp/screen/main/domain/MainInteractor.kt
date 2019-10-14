package com.alexvedenyapin.revoluttestapp.screen.main.domain

import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import com.alexvedenyapin.revoluttestapp.screen.main.data.CurrenciesRatesRepository
import com.alexvedenyapin.revoluttestapp.util.composeWith
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class MainInteractor(
    private val currenciesRatesRepository: CurrenciesRatesRepository,
    private val workers: RxWorkers
) {

    fun getCurrenciesRates(): Observable<Pair<String, List<CurrencyRate>>> =
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMap { currenciesRatesRepository.getCurrenciesRates() }
            .composeWith(workers)
}