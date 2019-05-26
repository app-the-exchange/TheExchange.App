package br.com.theexchange.presentation.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.theexchange.R
import br.com.theexchange.presentation.ui.AlertDialogCustom

open class BaseActivity : AppCompatActivity() {

    fun startActivityWithAnimation(context: Context, intent: Intent) {
        context.startActivity(intent);
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }


    fun showDialogErrorConnection(onClickListener: DialogInterface.OnClickListener) {
        val alertDialogInterface = AlertDialogCustom.Builder(this)
            .setTitle("Ops!")?.setMessage("Erro com conexão!")?.setOnClickPositiveButton("OK", null)
            ?.setOnClickPositiveButton("TENTAR NOVAMENTE", onClickListener)
            ?.show()
    }

    fun showDialogErrorGeneric(onClickListener: DialogInterface.OnClickListener) {
        val alertDialogInterface = AlertDialogCustom.Builder(this)
            .setTitle("Ops!")?.setMessage("Ocorreu um erro!")?.setOnClickPositiveButton("OK", null)
            ?.setOnClickPositiveButton("TENTAR NOVAMENTE", onClickListener)
            ?.show()
    }
}