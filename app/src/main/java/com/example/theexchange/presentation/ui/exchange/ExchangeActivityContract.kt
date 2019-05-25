package com.example.theexchange.presentation.ui.exchange

class ExchangeActivityContract {

    interface View {

        fun initView()

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter {

        fun start()
    }
}