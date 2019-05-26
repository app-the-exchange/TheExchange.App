package br.com.theexchange.data.store.remote.api.service

import br.com.theexchange.presentation.ui.exchange_category.model.ExchangeCategoryDTO
import br.com.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CountryService {
    @GET("api/Country")
    fun fetchCountries(): Single<Response<ArrayList<CountryDTO>>>

    @Headers("Accept: application/json")
    @GET("api/country/{id}")
    fun fetchCategory(@Path("id") id: Int): Single<Response<CountryDTO>>

    @GET("api/CategoryCustomer/{id}")
    fun fetchCategoryCustomer(@Path("id") id: Int): Single<Response<ArrayList<ExchangeCategoryDTO>>>
}