package com.example.theexchange.presentation.ui.country

import com.example.theexchange.presentation.ui.main.model.CountryDTO

interface CountryActivityContract {
    interface View {

        fun setupAndSetDataAdapter(country: CountryDTO)

        fun initView()

        fun hideLoading()

        fun showLoading()

        fun onError(error: Throwable)
    }

    interface Presenter {

        fun start(idCountry: Int)
    }
}