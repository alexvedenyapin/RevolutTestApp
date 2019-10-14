package com.alexvedenyapin.revoluttestapp.screen.main.domain

import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import com.alexvedenyapin.revoluttestapp.screen.main.data.CurrenciesRatesRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class MainInteractorTest {
    private val currenciesRatesRepository = mock(CurrenciesRatesRepository::class.java)
    private val interactor = MainInteractor(
        currenciesRatesRepository,
        RxWorkers(Schedulers.trampoline(), Schedulers.trampoline())
    )

    @Test
    fun `emit currencies rates list with selected currency`() {
        whenever(currenciesRatesRepository.getCurrenciesRates()).thenReturn(
            Observable.just(
                Pair(
                    "EUR",
                    mockCurrenciesRatesList()
                )
            )
        )

        val onNext: (Pair<String, List<CurrencyRate>>) -> Unit = mock()
        val onError: (Throwable) -> Unit = mock()
        whenever(onNext.invoke(any())).thenAnswer { }
        whenever(onError.invoke(any())).thenAnswer { }

        interactor.getCurrenciesRates(onNext, onError)

        verify(onNext, atLeastOnce()).invoke(any())
    }

    @Test
    fun `dont emit onNext when repository returns error`() {
        whenever(currenciesRatesRepository.getCurrenciesRates()).thenReturn(
            Observable.error(
                IllegalArgumentException()
            )
        )

        val onNext: (Pair<String, List<CurrencyRate>>) -> Unit = mock()
        val onError: (Throwable) -> Unit = mock()
        whenever(onNext.invoke(any())).thenAnswer { }
        whenever(onError.invoke(any())).thenAnswer { }

        interactor.getCurrenciesRates(onNext, onError)

        verify(onNext, never()).invoke(any())
    }

    @After
    fun release() {
        interactor.clearDisposable()
    }

    private fun mockCurrenciesRatesList() = listOf(
        CurrencyRate("EUR", 1.0),
        CurrencyRate("AUD", 1.01),
        CurrencyRate("BGN", 1.02),
        CurrencyRate("BRL", 1.03),
        CurrencyRate("CAD", 1.04)
    )
}