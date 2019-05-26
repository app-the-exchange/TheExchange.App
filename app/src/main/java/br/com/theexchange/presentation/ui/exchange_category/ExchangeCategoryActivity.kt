package br.com.theexchange.presentation.ui.exchange_category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.LinearLayoutManager
import br.com.theexchange.R
import br.com.theexchange.data.model.Category
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.ui.AlertDialogCustom
import br.com.theexchange.presentation.ui.CategoryDescription.CategoryDescriptionActivity
import br.com.theexchange.presentation.ui.category.CategoryActivity
import br.com.theexchange.presentation.ui.country.adapter.CategoryAdapter
import br.com.theexchange.presentation.ui.exchange.ExchangeActivity
import br.com.theexchange.presentation.ui.exchange_category.adapter.ExchangeCategoryAdapter
import br.com.theexchange.presentation.ui.exchange_category.model.ExchangeCategoryDTO
import br.com.theexchange.presentation.ui.main.fragment.FragmentCountries
import br.com.theexchange.presentation.ui.main.model.CountryDTO
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.activity_exchange.*
import kotlinx.android.synthetic.main.activity_exchange.toolbar
import kotlinx.android.synthetic.main.activity_exchange_category.*

class ExchangeCategoryActivity : AppCompatActivity(), ExchangeCategoryContract.View,
    ExchangeCategoryAdapter.OnClickCountryListener {

    private lateinit var alertDialogCustom: AlertDialogCustom

    private lateinit var mPresenter: ExchangeCategoryPresenter

    companion object {
        const val KEY_EXTRA_EXCHANGE = "KEY_EXTRA_EXCHANGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_category)

        val id = intent.getIntExtra(KEY_EXTRA_EXCHANGE, 1)

        mPresenter = ExchangeCategoryPresenter(this, ApiManager.apiInstance, id)

        initView()

        mPresenter.start()
    }

    override fun initView() {
        setupToolbar()
    }

    override fun setupAndSetDataAdapter(listExchangeCategoryDTO: ArrayList<ExchangeCategoryDTO>) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val mAdapter = ExchangeCategoryAdapter(listExchangeCategoryDTO, this, this)
        recyclerView.adapter = mAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.toolbar_title_exchange_category)
    }

    override fun hideLoading() {
        alertDialogCustom.dismiss()
    }

    override fun showLoading() {
        alertDialogCustom = AlertDialogCustom.LoadingBuilder(this).show()!!
    }

    override fun onClick(name: String) {
        val intent = Intent(this, CategoryDescriptionActivity::class.java)
        intent.putExtra(FragmentCountries.KEY_COUNTRY_NAME, name)
        startActivity(intent)
    }
}
