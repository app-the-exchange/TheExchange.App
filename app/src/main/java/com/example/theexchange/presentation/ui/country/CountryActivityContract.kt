package com.example.theexchange.presentation.ui.country

import com.example.theexchange.data.model.Category

interface CountryActivityContract {
    interface View {

        fun setupAdapter()

        fun initView()

        fun setRecyclerViewData(categoryList: ArrayList<Category>)

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun start(idCountry:Int)
    }
}