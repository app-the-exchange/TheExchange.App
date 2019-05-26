package br.com.theexchange.presentation.ui.main

import br.com.theexchange.data.store.remote.api.ApiManager

class MainActivityPresenter(
    private val apiManager: ApiManager,
    private val mView: MainActivityContract.View
) : MainActivityContract.Presenter {

    override fun start() {
        showFragment()
    }

    override fun showFragment() {
        mView.showFragment(TAG_FRAGMENT_COUNTRIES)
    }

    companion object {
        const val TAG_FRAGMENT_COUNTRIES = "TAG_FRAGMENT_COUNTRIES"
    }
}