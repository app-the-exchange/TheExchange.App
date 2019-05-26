package br.com.theexchange.presentation.ui.exchange

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import br.com.theexchange.R
import br.com.theexchange.data.model.Exchange
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.base.BaseActivity
import br.com.theexchange.presentation.ui.AlertDialogCustom
import br.com.theexchange.presentation.ui.CategoryDescription.CategoryDescriptionActivity
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
        setupListener()
    }

    override fun hideLoading() {
        alertDialogCustom.dismiss()
    }

    override fun setExchangeData(exchange: Exchange) {
        textViewCustomerName.text = exchange.name
        textViewCustomerPosition.text = exchange.position
        textViewName.text = exchange.name
        textViewEmail.text = exchange.email

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide
            .with(this)
            .load(exchange.country.banner_image)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(imageViewCountry)

        Glide
            .with(this)
            .load("https://i0.wp.com/marketingcomcafe.com.br/wp-content/uploads/2018/02/perfil-crach%C3%A1-500x500.jpg")
            .centerCrop()
            .placeholder(circularProgressDrawable)
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
        toolbar.title = getString(R.string.toolbar_title_exchange)
    }

    private fun setupListener() {
        button_exchange_info.setOnClickListener { startActivity(Intent(this, CategoryDescriptionActivity::class.java)) }

    }
}
