package com.example.theexchange.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.theexchange.R
import com.example.theexchange.data.store.remote.api.ApiManager
import com.example.theexchange.presentation.ui.exchange.ExchangeActivity
import com.example.theexchange.presentation.ui.main.fragment.FragmentCountries
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {

    private lateinit var mPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        initView()

        mPresenter = MainActivityPresenter(ApiManager.apiInstance, this)

        mPresenter.start()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_login -> {
            }
            R.id.nav_exchange -> {
            startActivity(Intent(this, ExchangeActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun initView() {
        setupToolbar()
        setupDrawer()
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun showFragment(fragmentTag: String) {
        when (fragmentTag) {
            MainActivityPresenter.TAG_FRAGMENT_COUNTRIES -> supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    FragmentCountries.newInstance(),
                    MainActivityPresenter.TAG_FRAGMENT_COUNTRIES
                ).commit()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.toolbar_title_country)
    }
}

