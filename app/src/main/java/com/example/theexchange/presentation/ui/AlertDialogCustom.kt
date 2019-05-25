package com.example.theexchange.presentation.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.theexchange.R

class AlertDialogCustom(mContext: Context) : AlertDialog(mContext) {

    private var isLoadingDialog = false

    override fun onStart() {
        super.onStart()

        if (isLoadingDialog) {
            setupLoading()
        }
    }

    private fun setupLoading() {
        this.setContentView(R.layout.alert_dialog_custom_loading)
        this.window.setBackgroundDrawable(null);
    }

    private fun setIsLoadingDialog() {
        this.isLoadingDialog = true
    }

    class LoadingBuilder(context: Context) {

        private val alertDialog = AlertDialogCustom(context)


        fun show(): AlertDialogCustom? {
            this.alertDialog.setIsLoadingDialog()
            this.alertDialog.show()
            return this.alertDialog
        }

        fun build(): AlertDialogCustom? {
            this.alertDialog.setIsLoadingDialog()
            return this.alertDialog
        }

    }


}