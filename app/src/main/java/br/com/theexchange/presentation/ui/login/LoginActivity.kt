package br.com.theexchange.presentation.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.theexchange.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    override fun initView() {
        setupToolbar()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
