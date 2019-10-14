package com.alexvedenyapin.revoluttestapp.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
data class CurrenciesRatesResponse(
    val base: String,
    val date: String,
    val rates: Rates
)

// Ideally, the api should return a list of objects that have fields: currency name and currency ratio.
// Thus one could easily parse that list without hardcoding currencies names or parse json manually
data class Rates(
    @SerializedName("AUD")
    val aud: String,
    @SerializedName("BGN")
    val bgn: String,
    @SerializedName("BRL")
    val brl: String,
    @SerializedName("CAD")
    val cad: String,
    @SerializedName("CHF")
    val chf: String,
    @SerializedName("CNY")
    val cny: String,
    @SerializedName("CZK")
    val czk: String,
    @SerializedName("DKK")
    val dkk: String,
    @SerializedName("GBP")
    val gbp: String,
    @SerializedName("HKD")
    val hkd: String,
    @SerializedName("HRK")
    val hrk: String,
    @SerializedName("HUF")
    val huf: String,
    @SerializedName("IDR")
    val idr: String,
    @SerializedName("ILS")
    val ils: String,
    @SerializedName("INR")
    val inr: String,
    @SerializedName("ISK")
    val isk: String,
    @SerializedName("JPY")
    val jpy: String,
    @SerializedName("KRW")
    val krw: String,
    @SerializedName("MXN")
    val mxn: String,
    @SerializedName("MYR")
    val myr: String,
    @SerializedName("NOK")
    val nok: String,
    @SerializedName("NZD")
    val nzd: String,
    @SerializedName("PHP")
    val php: String,
    @SerializedName("PLN")
    val pln: String,
    @SerializedName("RON")
    val ron: String,
    @SerializedName("RUB")
    val rub: String,
    @SerializedName("SEK")
    val sek: String,
    @SerializedName("SGD")
    val sgd: String,
    @SerializedName("THB")
    val thb: String,
    @SerializedName("TRY")
    val currencyTry: String,
    @SerializedName("USD")
    val usd: String,
    @SerializedName("ZAR")
    val zar: String
)