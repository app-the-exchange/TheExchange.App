package com.example.theexchange.presentation.ui.fly

interface FlyActivityContract {
    interface View {

        fun initView()

        fun hideLoading()

        fun showLoading()

    }

    interface Presenter {

        fun start()
    }
}