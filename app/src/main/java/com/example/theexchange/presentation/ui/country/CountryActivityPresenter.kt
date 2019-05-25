package com.example.theexchange.presentation.ui.country

import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CountryActivityPresenter(
    private val mView: CountryActivityContract.View,
    private val apiManager: ApiManager
) : CountryActivityContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun start(idCountry: Int) {
        requestFetchCountry(idCountry)
    }

    private fun requestFetchCountry(idCountry: Int) {
        mView.showLoading()

        val disposable = apiManager.getCountryService().fetchCategory(idCountry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::onRequestFetchCategorySuccess)
            .doOnError(this::onRequestError)
            .doFinally { mView.hideLoading() }
            .subscribe()

        mCompositeDisposable.add(disposable)
    }

    private fun onRequestFetchCategorySuccess(response: Response<CountryDTO>) {
        mView.setupAndSetDataAdapter(response.body() as CountryDTO)
    }

    private fun onRequestError(throwable: Throwable) {
        mView.onError(throwable)
    }
}