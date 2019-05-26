package br.com.theexchange.presentation.ui.exchange_category

import br.com.theexchange.data.model.Exchange
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.ui.exchange_category.model.ExchangeCategoryDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException

class ExchangeCategoryPresenter(
    private val mView: ExchangeCategoryContract.View,
    private val mApiManager: ApiManager,
    private val exchangeId: Int
) : ExchangeCategoryContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun start() {
        requestFetchExchange()
    }

    private fun requestFetchExchange() {
        mView.showLoading()

        val disposable = mApiManager.getCountryService().fetchCategoryCustomer(exchangeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::onRequestFetchExchange)
            .doOnError(this::onRequestError)
            .doFinally { mView.hideLoading() }
            .subscribe()

        mCompositeDisposable.add(disposable)
    }

    private fun onRequestFetchExchange(response: Response<ArrayList<ExchangeCategoryDTO>>) {
        mView.setupAndSetDataAdapter(response.body() as ArrayList<ExchangeCategoryDTO>)
    }

    private fun onRequestError(throwable: Throwable) {
        if (throwable is IOException) {

        } else {

        }
    }
}