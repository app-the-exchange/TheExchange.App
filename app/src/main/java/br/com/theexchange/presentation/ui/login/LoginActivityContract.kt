package br.com.theexchange.presentation.ui.login

interface LoginActivityContract {
    interface View {

        fun initView()

        fun hideLoading()

        fun showLoading()

    }

    interface Presenter {

        fun start()
    }
}