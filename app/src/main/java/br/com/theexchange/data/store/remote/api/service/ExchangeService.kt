package br.com.theexchange.data.store.remote.api.service

import br.com.theexchange.data.model.Exchange
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ExchangeService {

    @Headers("Accept: application/json")
    @GET("api/Customer/{id}")
    fun fetchExchange(@Path("id") id: Int): Single<Response<Exchange>>
}