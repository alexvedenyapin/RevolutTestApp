package com.alexvedenyapin.revoluttestapp.screen.main.data

import com.alexvedenyapin.revoluttestapp.model.CurrencyRateMapper
import com.alexvedenyapin.revoluttestapp.network.ApiService
import com.alexvedenyapin.revoluttestapp.network.response.CurrenciesRatesResponse
import com.alexvedenyapin.revoluttestapp.network.response.Rates
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class CurrenciesRatesRestRepositoryTest {
    private val apiService = mock(ApiService::class.java)
    private val currencyRateMapper = CurrencyRateMapper()
    private val repository = CurrenciesRatesRestRepository(apiService, currencyRateMapper)

    @Test
    fun `emit currencies rates list with selected currency`() {
        whenever(apiService.getCurrenciesRates()).thenReturn(
            Observable.just(
                mockCurrenciesRatesResponse()
            )
        )

        val resultPair = Pair("EUR", currencyRateMapper.mapResponseToCurrenciesList(mockRates()))

        repository.getCurrenciesRates()
            .test()
            .assertNoErrors()
            .assertValue { it == resultPair }
    }

    @Test
    fun `emit error when apiService returns error`() {
        whenever(apiService.getCurrenciesRates()).thenReturn(
            Observable.error(IllegalArgumentException())
        )

        repository.getCurrenciesRates()
            .test()
            .assertError(IllegalArgumentException::class.java)
    }

    private fun mockCurrenciesRatesResponse() =
        CurrenciesRatesResponse("EUR", "14.10.2019", mockRates())

    private fun mockRates() = Rates(
        aud = "1.01",
        bgn = "1.02",
        brl = "1.03",
        cad = "1.04",
        chf = "1.05",
        cny = "1.06",
        czk = "1.07",
        dkk = "1.08",
        gbp = "1.09",
        hkd = "1.10",
        hrk = "1.11",
        huf = "1.12",
        idr = "1.13",
        ils = "1.14",
        inr = "1.15",
        isk = "1.16",
        jpy = "1.17",
        krw = "1.18",
        mxn = "1.19",
        myr = "1.20",
        nok = "1.21",
        nzd = "1.22",
        php = "1.23",
        pln = "1.24",
        ron = "1.25",
        rub = "1.26",
        sek = "1.27",
        sgd = "1.28",
        thb = "1.29",
        currencyTry = "1.30",
        usd = "1.31",
        zar = "1.32"
    )
}