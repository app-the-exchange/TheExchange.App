package br.com.theexchange.presentation.ui.CategoryDescription

interface CategoryDescriptionActivityContract {
    interface View {

        fun initView()

        fun hideLoading()

        fun showLoading()

    }

    interface Presenter {

        fun start()
    }
}