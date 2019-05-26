package br.com.theexchange.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.theexchange.R
import br.com.theexchange.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    override fun initView() {
        setupToolbar()
        setupListener()
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

    private fun setupListener() {
        button_login_facebook.setOnClickListener {
            val intent = Intent()
            intent.action = MainActivity.ACTION_LOGIN
            sendBroadcast(intent)
            finish()
        }

        button_login_google.setOnClickListener {
            val intent = Intent()
            intent.action = MainActivity.ACTION_LOGIN
            sendBroadcast(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
