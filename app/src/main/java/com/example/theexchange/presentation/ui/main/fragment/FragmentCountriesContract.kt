package com.example.theexchange.presentation.ui.main.fragment

import com.example.theexchange.presentation.ui.main.model.CountryDTO

class FragmentCountriesContract {

    interface View {

        fun setupAdapter()

        fun initView()

        fun setRecyclerViewData(countriesList: ArrayList<CountryDTO>)
    }

    interface Presenter {

        fun onStart()
    }
}