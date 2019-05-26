package com.example.theexchange.presentation.ui.exchange

import com.example.theexchange.data.model.Exchange

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