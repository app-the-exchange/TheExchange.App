package br.com.theexchange.presentation.ui.country

import br.com.theexchange.presentation.ui.main.model.CountryDTO

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