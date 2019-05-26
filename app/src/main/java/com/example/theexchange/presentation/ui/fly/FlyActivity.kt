package com.example.theexchange.presentation.ui.fly

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.theexchange.R
import kotlinx.android.synthetic.main.activity_fly.*

class FlyActivity : AppCompatActivity(), FlyActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fly)

        initView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_fly)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_fly.title = getString(R.string.title_fly)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun initView() {
        setupToolbar()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
    }
}
