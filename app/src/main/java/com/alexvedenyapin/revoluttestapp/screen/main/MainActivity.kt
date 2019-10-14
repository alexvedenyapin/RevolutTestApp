package com.alexvedenyapin.revoluttestapp.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexvedenyapin.revoluttestapp.R
import com.alexvedenyapin.revoluttestapp.screen.main.di.MainComponentProvider
import com.alexvedenyapin.revoluttestapp.screen.main.ui.CurrenciesRatesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component by lazy {
        (application as MainComponentProvider).provideMainComponent(this)
    }

    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var ratesAdapter: CurrenciesRatesAdapter

    private val viewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        initViews()
        viewModel.doOnCreate()
    }

    private fun initViews() {
        ratesAdapter = CurrenciesRatesAdapter(::onCurrencyRateClicked, ::onCurrencyValueChanged)
        with(currenciesRatesList) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                .apply { orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL }
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                .apply { supportsChangeAnimations = true }
            adapter = ratesAdapter
        }

        viewModel.observeCurrenciesRates.observe(this, Observer { ratesAdapter.submitList(it) })
        viewModel.observeSelectedCurrencyRate.observe(this, Observer { ratesAdapter.setCurrency(it) })
    }

    private fun onCurrencyRateClicked(clickedItemCurrency: String) {
        viewModel.onItemClicked(clickedItemCurrency)
    }

    private fun onCurrencyValueChanged(newValue: Double) {
        viewModel.onCurrencyValueChanged(newValue)
    }
}