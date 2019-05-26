package br.com.theexchange.presentation.base

import android.content.DialogInterface
import android.support.v4.app.Fragment
import br.com.theexchange.presentation.ui.AlertDialogCustom

open class BaseFragment : Fragment() {

    fun showDialogErrorConnection(onClickListener: DialogInterface.OnClickListener) {
        val alertDialogInterface = AlertDialogCustom.Builder(context!!)
            .setTitle("Ops!")?.setMessage("Erro com conexão!")?.setOnClickPositiveButton("OK", null)
            ?.setOnClickPositiveButton("TENTAR NOVAMENTE", onClickListener)
            ?.show()
    }

    fun showDialogErrorGeneric(onClickListener: DialogInterface.OnClickListener) {
        val alertDialogInterface = AlertDialogCustom.Builder(context!!)
            .setTitle("Ops!")?.setMessage("Ocorreu um erro!")?.setOnClickPositiveButton("OK", null)
            ?.setOnClickPositiveButton("TENTAR NOVAMENTE", onClickListener)
            ?.show()
    }
}