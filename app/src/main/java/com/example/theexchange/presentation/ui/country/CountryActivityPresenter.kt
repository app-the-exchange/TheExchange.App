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
package com.example.theexchange.presentation.ui.country

<<<<<<< HEAD
import android.content.Context
import android.widget.Toast
import com.example.theexchange.data.model.Category
=======
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50
import com.example.theexchange.data.store.remote.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
<<<<<<< HEAD
import retrofit2.Response

class CountryActivityPresenter(
    private val mView: CountryActivityContract.View,
    private val apiManager: ApiManager,
    private val mContext: Context
=======

class CountryActivityPresenter(
    private val mView: CountryActivityContract.View,
    private val apiManager: ApiManager
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50
) : CountryActivityContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

<<<<<<< HEAD
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
=======
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
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50
    }
}