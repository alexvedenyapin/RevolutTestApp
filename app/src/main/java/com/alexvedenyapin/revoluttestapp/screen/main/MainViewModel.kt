package com.alexvedenyapin.revoluttestapp.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexvedenyapin.revoluttestapp.model.BASE_DEFAULT_RATIO
import com.alexvedenyapin.revoluttestapp.model.BASE_DEFAULT_VALUE
import com.alexvedenyapin.revoluttestapp.model.CurrencyRate
import com.alexvedenyapin.revoluttestapp.screen.main.domain.MainInteractor
import com.alexvedenyapin.revoluttestapp.screen.main.ui.CurrencyRateListItem
import timber.log.Timber
import kotlin.math.round

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class MainViewModel(private val mainInteractor: MainInteractor) : ViewModel() {
    private val currenciesRates = MutableLiveData<MutableList<CurrencyRateListItem>>()
    val observeCurrenciesRates: LiveData<MutableList<CurrencyRateListItem>>
        get() = currenciesRates
    private val selectedCurrencyRate = MutableLiveData<String>()
    val observeSelectedCurrencyRate: LiveData<String>
        get() = selectedCurrencyRate

    private var isInitialized: Boolean = false

    private var ratesList: MutableList<CurrencyRateListItem> = mutableListOf()
    private var baseCurrency: String? = null

    private var selectedCurrency: String = ""
    private var selectedRatio: Double = BASE_DEFAULT_RATIO
    private var selectedValue: Double = BASE_DEFAULT_VALUE
    private var selectedValueInBaseCurrency: Double = BASE_DEFAULT_VALUE

    fun doOnCreate() {
        if (isInitialized) return
        isInitialized = true

        mainInteractor.getCurrenciesRates(::onCurrenciesRatesAvailable) { Timber.e(it) }
    }

    fun onItemClicked(clickedItemCurrency: String) {
        val previouslySelectedItem = ratesList.find { it.currency == selectedCurrency }
        val clickedItem = ratesList.find { it.currency == clickedItemCurrency }
        updateSelectedCurrency(clickedItem)
        moveSelectedItemToTop(previouslySelectedItem, clickedItem)
    }

    fun onCurrencyValueChanged(newValue: Double) {
        selectedValue = newValue
    }

    override fun onCleared() {
        mainInteractor.clearDisposable()
    }

    private fun updateSelectedCurrency(clickedItem: CurrencyRateListItem?) {
        clickedItem?.let {
            selectedCurrency = clickedItem.currency
            selectedCurrencyRate.value = selectedCurrency
        }
    }

    private fun moveSelectedItemToTop(
        previouslySelectedItem: CurrencyRateListItem?,
        clickedItem: CurrencyRateListItem?
    ) {
        if (previouslySelectedItem != clickedItem && clickedItem != null) {
            val itemToMove: CurrencyRateListItem?
                selectedValue = clickedItem.value
            if (clickedItem.currency == baseCurrency) {
                selectedRatio = BASE_DEFAULT_RATIO
                itemToMove = clickedItem.copy(ratio = selectedRatio)
            } else {
                selectedRatio = clickedItem.ratio
                itemToMove = clickedItem
            }

            val updatedList = ratesList.toMutableList()

            updatedList.remove(clickedItem)
            updatedList.add(0, itemToMove)
            ratesList = updatedList
            currenciesRates.value = ratesList
        }
    }

    private fun onCurrenciesRatesAvailable(data: Pair<String, List<CurrencyRate>>) {
        if (selectedCurrency.isEmpty()) selectedCurrency = data.first

        baseCurrency = data.first
        val currenciesRatesListItems = data.second.map { currencyRate ->
            CurrencyRateListItem(currencyRate.currency, currencyRate.ratio, (currencyRate.ratio * selectedValue).roundValue())
        }
        updateItems(currenciesRatesListItems)
        currenciesRates.value = ratesList
    }

    private fun updateItems(newItems: List<CurrencyRateListItem>) {
        if (ratesList.isEmpty()) {
            ratesList.addAll(newItems)
            onItemClicked(selectedCurrency)
        } else {
            newItems.find { it.currency == selectedCurrency }?.let {
                selectedValueInBaseCurrency = (selectedValue / it.ratio).roundValue()
                selectedRatio = it.ratio
            }
            ratesList = updateList(newItems)
        }
    }

    private fun updateList(newItems: List<CurrencyRateListItem>): MutableList<CurrencyRateListItem> {
        val updatedList = mutableListOf<CurrencyRateListItem>()
        for (i in 0 until ratesList.size) {
            val item = ratesList[i]
            when {
                i == 0 -> {
                    if (item.value != selectedValue) {
                        item.value = selectedValue
                        updatedList.add(item)
                    } else {
                        updatedList.add(item)
                    }
                }
                item.currency == baseCurrency -> {
                    val baseCurrencyRatio = ((selectedValue / selectedRatio) / selectedValueInBaseCurrency).roundValue()
                    updatedList.add(item.copy(ratio = baseCurrencyRatio, value = (selectedValueInBaseCurrency * baseCurrencyRatio).roundValue()))
                }
                else -> {
                    val updatedItem = newItems.find { it.currency == item.currency }
                    updatedItem?.let {
                        updatedList.add(item.copy(ratio = updatedItem.ratio, value = (selectedValueInBaseCurrency * updatedItem.ratio).roundValue()))
                    }
                }
            }
        }
        return updatedList
    }

    private fun Double.roundValue(): Double {
        return round(this * 100) / 100
    }
}