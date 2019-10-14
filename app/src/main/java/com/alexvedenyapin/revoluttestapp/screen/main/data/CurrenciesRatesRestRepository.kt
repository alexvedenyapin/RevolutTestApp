package com.alexvedenyapin.revoluttestapp.screen.main.data

import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import com.alexvedenyapin.revoluttestapp.model.CurrencyRateMapper
import com.alexvedenyapin.revoluttestapp.network.ApiService
import io.reactivex.Observable

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class CurrenciesRatesRestRepository(
    private val apiService: ApiService,
    private val currencyRateMapper: CurrencyRateMapper
) : CurrenciesRatesRepository {
    override fun getCurrenciesRates(): Observable<Pair<String, List<CurrencyRate>>> {
        return apiService.getCurrenciesRates()
            .map { Pair(it.base, currencyRateMapper.mapResponseToCurrenciesList(it.rates)) }
    }
}