package com.example.theexchange.presentation.ui.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.theexchange.R
import com.example.theexchange.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_countries.*
import com.example.theexchange.presentation.ui.main.fragment.adapter.CountriesAdapter
import com.example.theexchange.presentation.ui.main.fragment.adapter.HeaderDecoration
import com.example.theexchange.presentation.ui.main.model.CountryDTO


class FragmentCountries : BaseFragment(), FragmentCountriesContract.View, CountriesAdapter.OnClickCountryListener {

    private val countriesList = ArrayList<CountryDTO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        setupAdapter()
    }

    override fun setupAdapter() {
        recyclerViewCountries.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val countryDTO = CountryDTO(
            1,
            "Canada",
            "A Irlanda, por vezes chamada República da Irlanda ou então Eire, é um Estado...",
            "https://cdn2.iconfinder.com/data/icons/world-flags-1-1/100/USA_-512.png"
        )

        countriesList.add(countryDTO)

        val adapter = CountriesAdapter(countriesList, activity!!.applicationContext, this)

        recyclerViewCountries.adapter = adapter

        val view = LayoutInflater.from(context).inflate(R.layout.header_recyclerview, null)

        val headerDecoration = HeaderDecoration(view, false, 10f, 0f, 1)
        recyclerViewCountries.addItemDecoration(headerDecoration)
    }

    override fun setRecyclerViewData(countriesList: ArrayList<CountryDTO>) {
        this.countriesList.addAll(countriesList)
    }

    override fun onClick(id: Int) {

    }

    companion object {

        @JvmStatic
        fun newInstance(): FragmentCountries {
            return FragmentCountries()
        }
    }


}
