package com.example.theexchange.presentation.ui.country

import android.content.Context
import android.widget.Toast
import com.example.theexchange.data.model.Category
import com.example.theexchange.data.store.remote.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CountryActivityPresenter(
    private val mView: CountryActivityContract.View,
    private val apiManager: ApiManager,
    private val mContext: Context
) : CountryActivityContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun start(idCountry:Int) {
        requestFetchCountry(idCountry)
    }

    private fun requestFetchCountry(idCountry: Int) {
        val disposable = apiManager.getCountryService().fetchCategory(idCountry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { this::onRequestFetchCountrySuccess }
            .doOnError(this::onRequestError)
            .doFinally { mView.hideLoading() }
            .subscribe()

        mCompositeDisposable.add(disposable)
    }

    private fun onRequestFetchCountrySuccess(response: Response<ArrayList<Category>>) {
        Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show()
    }

    private fun onRequestError(throwable: Throwable) {
        Toast.makeText(mContext, throwable.message, Toast.LENGTH_LONG).show()
    }
}