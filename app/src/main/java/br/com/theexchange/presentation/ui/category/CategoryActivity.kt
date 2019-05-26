package br.com.theexchange.presentation.ui.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.theexchange.R
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.ui.main.fragment.FragmentCountries
import br.com.theexchange.presentation.ui.main.model.CountryDTO
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryActivityContract.View {

    private lateinit var mPresenter: CategoryActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        mPresenter = CategoryActivityPresenter(this, ApiManager.apiInstance)

        initView()
    }

    override fun setupAndSetDataAdapter(country: CountryDTO) {
    }

    override fun initView() {
        if (intent.extras != null) {
            val nameCountry = intent.extras.getString(FragmentCountries.KEY_COUNTRY_NAME)
            mPresenter.start()
            setupToolbar(nameCountry)
        }
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
    }

    override fun onError(error: Throwable) {
    }

    private fun setupToolbar(nameCountry: String) {
        setSupportActionBar(toolbar_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_category.title = nameCountry
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
