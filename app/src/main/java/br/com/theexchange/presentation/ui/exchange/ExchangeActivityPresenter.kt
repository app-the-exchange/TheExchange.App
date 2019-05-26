package br.com.theexchange.presentation.ui.exchange

import br.com.theexchange.data.model.Exchange
import br.com.theexchange.data.store.remote.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException

class ExchangeActivityPresenter(
    private val mView: ExchangeActivityContract.View,
    private val mApiManager: ApiManager,
    private val exchangeId: Int
) : ExchangeActivityContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun start() {
        requestFetchExchange()
    }

    private fun requestFetchExchange() {
        mView.showLoading()

        val disposable = mApiManager.getExchangeService().fetchExchange(exchangeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::onRequestFetchExchange)
            .doOnError(this::onRequestError)
            .doFinally { mView.hideLoading() }
            .subscribe()

        mCompositeDisposable.add(disposable)
    }

    private fun onRequestFetchExchange(response: Response<Exchange>) {
        mView.setExchangeData(response.body() as Exchange)
    }

    private fun onRequestError(throwable: Throwable) {
        if (throwable is IOException) {

        } else {

        }
    }
}