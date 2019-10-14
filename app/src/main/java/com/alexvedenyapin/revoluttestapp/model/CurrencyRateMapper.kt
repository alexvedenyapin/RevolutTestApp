package com.alexvedenyapin.revoluttestapp.model

import com.alexvedenyapin.revoluttestapp.network.response.Rates

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */

private const val BASE_DEFAULT_RATIO = 1.0

class CurrencyRateMapper {
    fun mapResponseToCurrenciesList(rates: Rates) = listOf(
        CurrencyRate("EUR", BASE_DEFAULT_RATIO),
        CurrencyRate("AUD", rates.aud.toDouble()),
        CurrencyRate("BGN", rates.bgn.toDouble()),
        CurrencyRate("BRL", rates.brl.toDouble()),
        CurrencyRate("CAD", rates.cad.toDouble()),
        CurrencyRate("CHF", rates.chf.toDouble()),
        CurrencyRate("CNY", rates.cny.toDouble()),
        CurrencyRate("CZK", rates.czk.toDouble()),
        CurrencyRate("DKK", rates.dkk.toDouble()),
        CurrencyRate("GBP", rates.gbp.toDouble()),
        CurrencyRate("HKD", rates.hkd.toDouble()),
        CurrencyRate("HRK", rates.hrk.toDouble()),
        CurrencyRate("HUF", rates.huf.toDouble()),
        CurrencyRate("IDR", rates.idr.toDouble()),
        CurrencyRate("ILS", rates.ils.toDouble()),
        CurrencyRate("INR", rates.inr.toDouble()),
        CurrencyRate("ISK", rates.isk.toDouble()),
        CurrencyRate("JPY", rates.jpy.toDouble()),
        CurrencyRate("KRW", rates.krw.toDouble()),
        CurrencyRate("MXN", rates.mxn.toDouble()),
        CurrencyRate("MYR", rates.myr.toDouble()),
        CurrencyRate("NOK", rates.nok.toDouble()),
        CurrencyRate("NZD", rates.nzd.toDouble()),
        CurrencyRate("PHP", rates.php.toDouble()),
        CurrencyRate("PLN", rates.pln.toDouble()),
        CurrencyRate("RON", rates.ron.toDouble()),
        CurrencyRate("RUB", rates.rub.toDouble()),
        CurrencyRate("SEK", rates.sek.toDouble()),
        CurrencyRate("SGD", rates.sgd.toDouble()),
        CurrencyRate("THB", rates.thb.toDouble()),
        CurrencyRate("TRY", rates.currencyTry.toDouble()),
        CurrencyRate("USD", rates.usd.toDouble()),
        CurrencyRate("ZAR", rates.zar.toDouble())
    )
}