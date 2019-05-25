package com.example.theexchange.presentation.ui.country

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.theexchange.R
import com.example.theexchange.presentation.ui.main.MainActivity
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.app_bar_main.*

class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_country)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
