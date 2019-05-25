package com.example.theexchange.data.store.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


internal class ApiAdapterFactory private constructor(private val apiEndpoint: String, private val timeout: Int?) {

    private fun buildAdapter(): Retrofit {
        val client = OkHttpClient.Builder()

        //if necessary put header in the request
        //client.addInterceptor(getHeaderInterceptor());

        if (timeout != null) {
            client.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        fun createAdapter(apiEndpoint: String, timeout: Int?): Retrofit {
            val factory = ApiAdapterFactory(apiEndpoint, timeout)
            return factory.buildAdapter()
        }
    }

}