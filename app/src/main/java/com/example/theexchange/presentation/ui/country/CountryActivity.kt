package com.example.theexchange.presentation.ui.country

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.theexchange.R
import com.example.theexchange.data.model.Category
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity(), CountryActivityContract.View, CategoryAdapter.OnClickCountryListener {

    private lateinit var mPresenter: CountryActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        mPresenter = CountryActivityPresenter(this, ApiManager.apiInstance)

        initView()

        if (intent.extras != null) {
            val idCountry = intent.extras.getInt(FragmentCountries.KEY_COUNTRY_ID)
            val nameCountry = intent.extras.getString(FragmentCountries.KEY_COUNTRY_NAME)
            setupToolbar(nameCountry)
            mPresenter.requestFetchCountry(idCountry)
        }
    }

    override fun handleResponse(country: CountryDTO) {
        Glide
            .with(this)
            .load(country.banner_image)
            .centerCrop()
            .into(country_selected)

        content_destiny_country.text = country.description1
        content_attractions_country.text = country.description2

        recyclerViewCategory.layoutManager = LinearLayoutManager(this)
        recyclerViewCategory.setHasFixedSize(true)
        val mAdapter = CategoryAdapter(country.categories as MutableList<Category>, this, this)
        recyclerViewCategory.adapter = mAdapter
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }


    override fun initView() {
    }

    override fun onClick(id: Int) {
        Toast.makeText(this, "Description category", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
    }

    private fun setupToolbar(nameCountry: String) {
        setSupportActionBar(toolbar_country)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_country.title = nameCountry
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
