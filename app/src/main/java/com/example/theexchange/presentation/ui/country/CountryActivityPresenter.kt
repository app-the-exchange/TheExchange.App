package com.example.theexchange.presentation.ui.country

import com.example.theexchange.data.store.remote.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountryActivityPresenter(
    private val mView: CountryActivityContract.View,
    private val apiManager: ApiManager
) : CountryActivityContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun requestFetchCountry(idCountry: Int) {
        mCompositeDisposable.add(
            apiManager.getCountryService().fetchCategory(idCountry)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { resp -> mView.handleResponse(resp) },
                    { error -> mView.handleError(error) }
                )
        )
    }
}