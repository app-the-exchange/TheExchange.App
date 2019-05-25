package com.example.theexchange.presentation.ui.country

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import android.support.v7.widget.LinearLayoutManager
<<<<<<< HEAD
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.theexchange.R
import com.example.theexchange.data.model.Category
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
<<<<<<< HEAD
import com.example.theexchange.presentation.base.BaseActivity
import com.example.theexchange.presentation.ui.main.MainActivity
=======
import com.example.theexchange.data.model.Category
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
>>>>>>> d04e2d4107154aca84f5cd65d52170e35c1b7d4e
=======
import android.widget.Toast
import com.example.theexchange.R
import com.example.theexchange.data.model.Category
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.country.adapter.CategoryAdapter
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
import com.example.theexchange.presentation.ui.main.fragment.adapter.HeaderDecoration
import com.example.theexchange.presentation.ui.main.model.CountryDTO
import kotlinx.android.synthetic.main.activity_country.*
<<<<<<< HEAD
import kotlinx.android.synthetic.main.fragment_countries.*

class CountryActivity : AppCompatActivity(), CountryActivityContract.View, CategoryAdapter.OnClickCountryListener {

    private lateinit var mPresenter: CountryActivityPresenter

<<<<<<< HEAD
class CountryActivity : BaseActivity() {
=======
    private val categoryList = ArrayList<Category>()

    private lateinit var mPresenter: CountryActivityPresenter
>>>>>>> d04e2d4107154aca84f5cd65d52170e35c1b7d4e
=======

class CountryActivity : AppCompatActivity(), CountryActivityContract.View, CategoryAdapter.OnClickCountryListener {

    private lateinit var mPresenter: CountryActivityPresenter
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50

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
<<<<<<< HEAD
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

=======
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
>>>>>>> 0194c84d499e90c327c2d8f4519f3eaac4654b50
        setupToolbar()
        setupAdapter()
    }

    override fun onClick(id: Int) {
        Toast.makeText(this, "Description category", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
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
