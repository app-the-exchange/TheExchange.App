package com.example.theexchange.presentation.ui.country

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.theexchange.R
import com.example.theexchange.data.model.Category
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
import com.example.theexchange.presentation.ui.main.fragment.adapter.HeaderDecoration
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.fragment_countries.*

class CountryActivity : AppCompatActivity(), CountryActivityContract.View, CategoryAdapter.OnClickCountryListener {

    private val categoryList = ArrayList<Category>()

    private lateinit var mPresenter: CountryActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        initView()
    }

    override fun setupAdapter() {
        recyclerViewCountries.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = CategoryAdapter(categoryList, this.applicationContext, this)
        recyclerViewCountries.adapter = adapter
        val view = LayoutInflater.from(this).inflate(R.layout.header_recyclerview, null)
        val headerDecoration = HeaderDecoration(view, false, 10f, 0f, 1)
        recyclerViewCountries.addItemDecoration(headerDecoration)
    }

    override fun initView() {
        if (intent.extras != null){
            val idCountry = intent.extras.getInt(FragmentCountries.KEY_COUNTRY_ID)
            mPresenter.start(idCountry)
        }

        setupToolbar()
        setupAdapter()
    }

    override fun onClick(id: Int) {
        Toast.makeText(this,"Description category",Toast.LENGTH_LONG).show()
    }

    override fun setRecyclerViewData(categoryList: ArrayList<Category>) {
        this.categoryList.addAll(categoryList)
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
