package com.example.theexchange.presentation.ui.exchange

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.theexchange.R
import com.example.theexchange.data.model.Exchange
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.base.BaseActivity
import com.example.theexchange.presentation.ui.AlertDialogCustom
import kotlinx.android.synthetic.main.activity_exchange.*

class ExchangeActivity : BaseActivity(), ExchangeActivityContract.View {

    private lateinit var alertDialogCustom: AlertDialogCustom

    private lateinit var mPresenter: ExchangeActivityPresenter

    companion object {
        const val KEY_EXTRA_EXCHANGE = "KEY_EXTRA_EXCHANGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        val id = intent.getIntExtra(KEY_EXTRA_EXCHANGE, 1)

        mPresenter = ExchangeActivityPresenter(this, ApiManager.apiInstance, id)

        mPresenter.start()

        initView()
    }

    override fun initView() {
        setupToolbar()
    }

    override fun hideLoading() {
        alertDialogCustom.dismiss()
    }

    override fun setExchangeData(exchange: Exchange) {
        textViewCustomerName.text = exchange.name
        textViewCustomerPosition.text = exchange.position
        textViewName.text = exchange.name
        textViewEmail.text = exchange.email

        Glide
            .with(this)
            .load(exchange.country.banner_image)
            .centerCrop()
            .placeholder(R.drawable.ic_canada)
            .into(imageViewCountry)

        Glide
            .with(this)
            .load("https://scontent.faqa1-1.fna.fbcdn.net/v/t1.0-1/p240x240/16681473_1265882800171472_5661372089732543956_n.jpg?_nc_cat=109&_nc_pt=1&_nc_ht=scontent.faqa1-1.fna&oh=1d37d733300d78caee58c8267cf9a609&oe=5D9311C6")
            .centerCrop()
            .placeholder(R.drawable.ic_canada)
            .into(circleImageViewCustomer)
    }

    override fun showLoading() {
        alertDialogCustom = AlertDialogCustom.LoadingBuilder(this).show()!!
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.toolbar_title_country)
    }
}
