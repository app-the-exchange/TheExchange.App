package com.example.theexchange.presentation.ui.exchange

import com.example.theexchange.data.store.remote.api.ApiManager

class ExchangeActivityPresenter (
    private val mView: ExchangeActivityContract.View,
    private val mApiManager: ApiManager) : ExchangeActivityContract.Presenter {


    override fun start() {
    }

    private fun requestFetchExchange(){

    }
}