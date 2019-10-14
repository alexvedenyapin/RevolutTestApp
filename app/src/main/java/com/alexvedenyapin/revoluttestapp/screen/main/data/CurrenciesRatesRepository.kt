package com.alexvedenyapin.revoluttestapp.screen.main.data

import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import io.reactivex.Observable

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
interface CurrenciesRatesRepository {
    fun getCurrenciesRates(): Observable<Pair<String, List<CurrencyRate>>>
}