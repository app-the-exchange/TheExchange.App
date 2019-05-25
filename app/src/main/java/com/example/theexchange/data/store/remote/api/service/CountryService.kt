package com.example.theexchange.data.store.remote.api.service

import com.example.theexchange.data.model.Category
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CountryService {
    @GET("api/Country")
    fun fetchCountries(): Single<Response<ArrayList<CountryDTO>>>

    @Headers("Accept: application/json")
    @GET("api/category/{id}")
    fun fetchCategory(@Path("id") id: Int): Observable<List<Category>>
}