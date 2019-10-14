package com.alexvedenyapin.revoluttestapp.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexvedenyapin.revoluttestapp.screen.main.domain.MainInteractor
import com.alexvedenyapin.revoluttestapp.screen.main.ui.CurrencyRateListItem

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

    fun doOnCreate() {

    }

    fun onItemClicked(clickedItemCurrency: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onCurrencyValueChanged(newValue: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}