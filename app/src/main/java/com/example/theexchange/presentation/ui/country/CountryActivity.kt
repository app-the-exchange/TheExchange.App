package com.example.theexchange.presentation.ui.country

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.theexchange.R
import com.example.theexchange.data.model.Category
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
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
            mPresenter.requestFetchCountry(idCountry)
        }
    }

    override fun handleResponse(categoryList: List<Category>) {
        recyclerViewCategory.layoutManager = LinearLayoutManager(this)
        recyclerViewCategory.setHasFixedSize(true)
        val mAdapter = CategoryAdapter(categoryList as MutableList<Category>,this,this)
        recyclerViewCategory.adapter = mAdapter
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }


    override fun initView() {
        setupToolbar()
    }

    override fun onClick(id: Int) {
        Toast.makeText(this, "Description category", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
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
