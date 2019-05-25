package com.example.theexchange.data.store.remote.api.service

import com.example.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {

    @GET("/")
    fun getCustomerUser(): Single<Response<CountryDTO>>
}