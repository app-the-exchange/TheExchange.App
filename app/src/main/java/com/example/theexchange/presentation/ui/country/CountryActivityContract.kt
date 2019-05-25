package com.example.theexchange.presentation.ui.country

import com.example.theexchange.presentation.ui.main.model.CountryDTO

interface CountryActivityContract {
    interface View {

        fun handleResponse(country: CountryDTO)
        fun handleError(error: Throwable)

        fun initView()

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun requestFetchCountry(idCountry: Int)
    }
}