package br.com.theexchange.presentation.ui.exchange

import br.com.theexchange.data.model.Exchange

interface ExchangeActivityContract {

    interface View {

        fun initView()

        fun hideLoading()

        fun showLoading()

        fun setExchangeData(exchange: Exchange)
    }

    interface Presenter {

        fun start()
    }
}