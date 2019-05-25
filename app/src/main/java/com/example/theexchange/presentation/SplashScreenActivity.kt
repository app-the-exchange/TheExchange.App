package com.example.theexchange.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.theexchange.R
import com.example.theexchange.presentation.base.BaseActivity
import com.example.theexchange.presentation.ui.main.MainActivity

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        showMainActivityWithAnimation()
    }


    private fun showMainActivityWithAnimation() {
        val handler = Handler()
        handler.postDelayed(
            Runnable {
                startActivityWithAnimation(this, Intent(this, MainActivity::class.java))
            }, 4000
        )
    }
}
