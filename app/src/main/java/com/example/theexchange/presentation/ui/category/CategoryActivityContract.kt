package com.example.theexchange.presentation.ui.category

import com.example.theexchange.presentation.ui.main.model.CountryDTO

interface CategoryActivityContract {
    interface View {

        fun setupAndSetDataAdapter(country: CountryDTO)

        fun initView()

        fun hideLoading()

        fun showLoading()

        fun onError(error: Throwable)
    }

    interface Presenter {

        fun start()
    }
}