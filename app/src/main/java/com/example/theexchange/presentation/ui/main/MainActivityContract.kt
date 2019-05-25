package com.example.theexchange.presentation.ui.main

class MainActivityContract {

    interface View{

        fun initView()

        fun showFragment(fragmentTag: String)
    }

    interface Presenter{

        fun start()

        fun showFragment()
    }
}