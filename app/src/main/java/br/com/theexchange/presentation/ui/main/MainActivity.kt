package br.com.theexchange.presentation.ui.main

import android.content.*
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import br.com.theexchange.R
import com.bumptech.glide.Glide
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.ui.AlertDialogCustom
import br.com.theexchange.presentation.ui.exchange.ExchangeActivity
import br.com.theexchange.presentation.ui.login.LoginActivity
import br.com.theexchange.presentation.ui.main.fragment.FragmentCountries
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {

    companion object {
        const val ACTION_LOGIN = "ACTION_LOGIN"
    }

    private lateinit var searchView: SearchView
    private lateinit var mPresenter: MainActivityPresenter
    private lateinit var mBroadcastReceiver: BroadcastReceiver
    private lateinit var fragmentCountries: FragmentCountries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        initView()

        mPresenter = MainActivityPresenter(ApiManager.apiInstance, this)

        mPresenter.start()

        setupBroadcastReceiver()
    }

    private fun showDialogLogoff() {
        val alertDialogCustom = AlertDialogCustom.Builder(this)
            .setMessage("Sair da conta?")
            ?.setOnClickPositiveButton("Sim", DialogInterface.OnClickListener { dialog, which -> onLogoff() })
            ?.setOnClickNegative("Não", null)?.show()
    }

    private fun onLogoff() {
        val headerLayout = nav_view.getHeaderView(0)
        headerLayout.user_nome.text = getString(R.string.sign_in)
        headerLayout.user_email.text = ""

        nav_view.menu.findItem(R.id.nav_login).isVisible = true
        nav_view.menu.findItem(R.id.nav_logoff).isVisible = false
        drawer_layout.closeDrawer(Gravity.START)

        headerLayout.img_drawer_perfil.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_placeholder_user))
    }

    private fun setupBroadcastReceiver() {
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

                nav_view.menu.findItem(R.id.nav_login).isVisible = false
                nav_view.menu.findItem(R.id.nav_exchange).isVisible = true
                nav_view.menu.findItem(R.id.nav_logoff).isVisible = true
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
                startActivity(Intent(this, ExchangeActivity::class.java));
                drawer_layout.closeDrawer(Gravity.START)
            }
            R.id.nav_logoff -> {
                showDialogLogoff()
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
        fragmentCountries = FragmentCountries.newInstance()
        when (fragmentTag) {
            MainActivityPresenter.TAG_FRAGMENT_COUNTRIES -> supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    fragmentCountries,
                    MainActivityPresenter.TAG_FRAGMENT_COUNTRIES
                ).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fragmentCountries.mAdapter.filter.filter(newText)
                return true
            }
        })
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.toolbar_title_country)
    }
}

