package br.com.theexchange.presentation.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.theexchange.R
import com.bumptech.glide.Glide
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.ui.exchange.ExchangeActivity
import br.com.theexchange.presentation.ui.login.LoginActivity
import br.com.theexchange.presentation.ui.main.fragment.FragmentCountries
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {

    companion object{
        const val ACTION_LOGIN = "ACTION_LOGIN"
    }

    private lateinit var mPresenter: MainActivityPresenter

    private lateinit var mBroadcastReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        initView()

        mPresenter = MainActivityPresenter(ApiManager.apiInstance, this)

        mPresenter.start()

        setupBroadcastReceiver()
    }

    private fun setupBroadcastReceiver(){
        mBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(contxt: Context, intent: Intent) {
                val headerLayout = nav_view.getHeaderView(0)
                val circularProgressDrawable = CircularProgressDrawable(contxt)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()
                Glide.with(contxt)
                    .load("https://i0.wp.com/marketingcomcafe.com.br/wp-content/uploads/2018/02/perfil-crach%C3%A1-500x500.jpg")
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(headerLayout.img_drawer_perfil)

                headerLayout.user_nome.text = "Fábio Almeida"
                headerLayout.user_email.text = "fabio.almeida@gmail.com"

                drawer_layout.isDrawerOpen(GravityCompat.START)
                
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_LOGIN)

        registerReceiver(mBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
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
            startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_exchange -> {
            startActivity(Intent(this, ExchangeActivity::class.java))
            }
        }
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

