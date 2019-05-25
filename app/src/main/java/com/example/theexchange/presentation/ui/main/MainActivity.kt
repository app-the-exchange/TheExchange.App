package com.example.theexchange.presentation.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.theexchange.R
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teste()
    }

    private fun teste(){
        val disposable = ApiManager.apiInstance.countryService.getCustomerUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::onRequestSuccess)
            .doFinally {  }.subscribe()

            mCompositeDisposable.add(disposable)
    }

    private fun onRequestSuccess(response: Response<CountryDTO>){
        Toast.makeText(applicationContext, (response.body() as CountryDTO).nome, Toast.LENGTH_SHORT).show()
    }
}

