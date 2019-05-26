package br.com.theexchange.presentation.ui.exchange_category

import br.com.theexchange.presentation.ui.exchange_category.model.ExchangeCategoryDTO
import br.com.theexchange.presentation.ui.main.model.CountryDTO

class ExchangeCategoryContract {

    interface View {

        fun setupAndSetDataAdapter(listExchangeCategoryDTO: ArrayList<ExchangeCategoryDTO>)

        fun initView()

        fun hideLoading()

        fun showLoading()

    }

    interface Presenter {

        fun start()

    }
}