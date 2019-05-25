package com.example.theexchange.presentation.ui.main.fragment

import com.example.theexchange.presentation.ui.main.model.CountryDTO

interface FragmentCountriesContract {

    interface View {

        fun initView()

        fun setupAndSetDataAdapter(countriesList: ArrayList<CountryDTO>)

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun start()
    }
}