package br.com.theexchange.presentation.ui.CategoryDescription

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.theexchange.R
import kotlinx.android.synthetic.main.activity_category_description.*

class CategoryDescriptionActivity : AppCompatActivity(), CategoryDescriptionActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_description)

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
