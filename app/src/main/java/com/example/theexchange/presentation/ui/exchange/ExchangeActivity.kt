package com.example.theexchange.presentation.ui.exchange

import android.os.Bundle
import com.example.theexchange.R
import com.example.theexchange.presentation.base.BaseActivity
import com.example.theexchange.presentation.ui.AlertDialogCustom
import kotlinx.android.synthetic.main.activity_exchange.*

class ExchangeActivity : BaseActivity(), ExchangeActivityContract.View {

    private lateinit var alertDialogCustom: AlertDialogCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
    }

    override fun initView() {
        setupToolbar()
    }

    override fun hideLoading() {
        alertDialogCustom.dismiss()
    }

    override fun showLoading() {
        alertDialogCustom = AlertDialogCustom.LoadingBuilder(applicationContext).show()!!
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
