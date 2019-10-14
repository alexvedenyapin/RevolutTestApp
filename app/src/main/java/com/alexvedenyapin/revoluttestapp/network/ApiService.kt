package com.alexvedenyapin.revoluttestapp.network

import com.alexvedenyapin.revoluttestapp.network.response.CurrenciesRatesResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
interface ApiService {

    companion object {
        fun create(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://revolut.duckdns.org/")
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("latest?base=EUR")
    fun getCurrenciesRates(): Observable<CurrenciesRatesResponse>
}