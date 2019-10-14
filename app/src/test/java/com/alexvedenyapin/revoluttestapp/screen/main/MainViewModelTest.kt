@file:Suppress("UNCHECKED_CAST")

package com.alexvedenyapin.revoluttestapp.screen.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import com.alexvedenyapin.revoluttestapp.screen.main.domain.MainInteractor
import com.alexvedenyapin.revoluttestapp.screen.main.ui.CurrencyRateListItem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class MainViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val interactor = mock(MainInteractor::class.java)
    private val viewModel = MainViewModel(interactor)

    @Before
    fun prepare() {
        whenever(interactor.getCurrenciesRates(any(), any())).thenAnswer {
            (it.arguments.first() as (Pair<String, List<CurrencyRate>>) -> Unit).invoke(Pair("EUR", mockCurrenciesRatesList())) }
    }

    @Test
    fun `get list of items and selected currency on create`() {
        viewModel.doOnCreate()

        val expectedItems = listOf(
            CurrencyRateListItem("EUR", 1.0, 100.0),
            CurrencyRateListItem("AUD", 1.01, 101.0),
            CurrencyRateListItem("BGN", 1.02, 102.0),
            CurrencyRateListItem("BRL", 1.03, 103.0),
            CurrencyRateListItem("CAD", 1.04, 104.0)
        )

        val selectedCurrency = viewModel.observeSelectedCurrencyRate.value!!
        val resultItems = viewModel.observeCurrenciesRates.value!!
        assertEquals("EUR", selectedCurrency)
        assertEquals(resultItems, expectedItems)
    }

    @Test
    fun `move currency to top on item click`() {
        viewModel.doOnCreate()
        viewModel.onItemClicked("CAD")

        val expectedItems = listOf(
            CurrencyRateListItem("CAD", 1.04, 104.0),
            CurrencyRateListItem("EUR", 1.0, 100.0),
            CurrencyRateListItem("AUD", 1.01, 101.0),
            CurrencyRateListItem("BGN", 1.02, 102.0),
            CurrencyRateListItem("BRL", 1.03, 103.0)
        )

        val selectedCurrency = viewModel.observeSelectedCurrencyRate.value!!
        val resultItems = viewModel.observeCurrenciesRates.value!!
        assertEquals("CAD", selectedCurrency)
        assertEquals(resultItems, expectedItems)
    }

    @Test
    fun `change nothing on already selected item click`() {
        viewModel.doOnCreate()
        viewModel.onItemClicked("EUR")

        val expectedItems = listOf(
            CurrencyRateListItem("EUR", 1.0, 100.0),
            CurrencyRateListItem("AUD", 1.01, 101.0),
            CurrencyRateListItem("BGN", 1.02, 102.0),
            CurrencyRateListItem("BRL", 1.03, 103.0),
            CurrencyRateListItem("CAD", 1.04, 104.0)
        )

        val selectedCurrency = viewModel.observeSelectedCurrencyRate.value!!
        val resultItems = viewModel.observeCurrenciesRates.value!!
        assertEquals("EUR", selectedCurrency)
        assertEquals(resultItems, expectedItems)
    }

    @Test
    fun `update rates values on selected currency value changed`() {
        viewModel.onCurrencyValueChanged(500.0)
        viewModel.doOnCreate()

        val expectedItems = listOf(
            CurrencyRateListItem("EUR", 1.0, 500.0),
            CurrencyRateListItem("AUD", 1.01, 505.0),
            CurrencyRateListItem("BGN", 1.02, 510.0),
            CurrencyRateListItem("BRL", 1.03, 515.0),
            CurrencyRateListItem("CAD", 1.04, 520.0)
        )

        val resultItems = viewModel.observeCurrenciesRates.value!!
        assertEquals(resultItems, expectedItems)
    }

    @Test
    fun `do on create only once`() {
        viewModel.doOnCreate()
        viewModel.doOnCreate()
        viewModel.doOnCreate()

        verify(interactor, times(1)).getCurrenciesRates(any(), any())
    }

    private fun mockCurrenciesRatesList() = listOf(
        CurrencyRate("EUR", 1.0),
        CurrencyRate("AUD", 1.01),
        CurrencyRate("BGN", 1.02),
        CurrencyRate("BRL", 1.03),
        CurrencyRate("CAD", 1.04)
    )
}