package br.com.theexchange.presentation.ui.main.fragment

import br.com.theexchange.presentation.ui.main.model.CountryDTO

interface FragmentCountriesContract {

    interface View {

        fun initView()

        fun setupAndSetDataAdapter(countriesList: ArrayList<CountryDTO>)

        fun onErrorConection()

        fun onErrorGeneric()

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun start()
    }
}