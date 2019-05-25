package com.example.theexchange.presentation.base

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.theexchange.R

open class BaseActivity : AppCompatActivity() {

    fun startActivityWithAnimation(context: Context, intent: Intent) {
        context.startActivity(intent);
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }
}