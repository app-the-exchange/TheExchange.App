package com.example.theexchange.presentation.ui.main.fragment

import android.util.Log
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class FragmentCountriesPresenter(
    private val mView: FragmentCountriesContract.View,
    private val apiManager: ApiManager
) : FragmentCountriesContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun start() {
        requestFetchCountries()
    }

    private fun requestFetchCountries() {
        val disposable = apiManager.getCountryService().fetchCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { this::onRequestFetchCountriesSuccess }
            .doOnError(this::onRequestError)
            .doFinally { mView?.hideLoading() }
            .subscribe()

        mCompositeDisposable.add(disposable)
    }

    private fun onRequestFetchCountriesSuccess(response: Response<ArrayList<CountryDTO>>) {
   Log.i("oi", "oiii")
    }

    private fun onRequestError(throwable: Throwable) {

    }


}