package br.com.theexchange.presentation.ui.main.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.theexchange.R
import br.com.theexchange.data.store.remote.api.ApiManager
import br.com.theexchange.presentation.base.BaseFragment
import br.com.theexchange.presentation.ui.country.CountryActivity
import br.com.theexchange.presentation.ui.AlertDialogCustom
import kotlinx.android.synthetic.main.fragment_countries.*
import br.com.theexchange.presentation.ui.main.fragment.adapter.CountriesAdapter
import br.com.theexchange.presentation.ui.main.fragment.adapter.HeaderDecoration
import br.com.theexchange.presentation.ui.main.model.CountryDTO

class FragmentCountries : BaseFragment(), FragmentCountriesContract.View, CountriesAdapter.OnClickCountryListener {

    private val countriesList = ArrayList<CountryDTO>()

    private lateinit var mPresenter: FragmentCountriesPresenter
    private lateinit var alertDialogCustom: AlertDialogCustom
    lateinit var mAdapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = FragmentCountriesPresenter(this, ApiManager.apiInstance)

        initView()

        mPresenter.start()

    }

    override fun initView() {

    }

    override fun setupAndSetDataAdapter(countriesList: ArrayList<CountryDTO>) {
        this.countriesList.addAll(countriesList)

        recyclerViewCountries.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        mAdapter = CountriesAdapter(this.countriesList, activity!!.applicationContext, this)

        recyclerViewCountries.adapter = mAdapter

        val view = LayoutInflater.from(context).inflate(R.layout.header_recyclerview, null)

        val headerDecoration = HeaderDecoration(view, false, 10f, 0f, 1)
        recyclerViewCountries.addItemDecoration(headerDecoration)
    }
    
    override fun onClick(id: Int,name:String) {
        val intent = Intent(context, CountryActivity::class.java)
        intent.putExtra(KEY_COUNTRY_ID, id)
        intent.putExtra(KEY_COUNTRY_NAME,name)
        context?.startActivity(intent)
    }

    override fun hideLoading() {
        alertDialogCustom.dismiss()
    }

    override fun showLoading() {
        alertDialogCustom = AlertDialogCustom.LoadingBuilder(context!!).show()!!
    }

    override fun onErrorConection() {
        showDialogErrorGeneric(DialogInterface.OnClickListener { dialog, which -> mPresenter.tryAgainRequest()  })
    }

    override fun onErrorGeneric() {
        showDialogErrorGeneric(DialogInterface.OnClickListener { dialog, which -> mPresenter.tryAgainRequest()  })
    }

    companion object {
        const val KEY_COUNTRY_ID = "KEY_COUNTRY_ID"
        const val KEY_COUNTRY_NAME = "KEY_COUNTRY_NAME"

        @JvmStatic
        fun newInstance(): FragmentCountries {
            return FragmentCountries()
        }
    }


}
