package com.example.theexchange.presentation.ui.country

import com.example.theexchange.data.model.Category

interface CountryActivityContract {
    interface View {

        fun handleResponse(categoryList: List<Category>)
        fun handleError(error: Throwable)

        fun initView()

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun requestFetchCountry(idCountry: Int)
    }
}