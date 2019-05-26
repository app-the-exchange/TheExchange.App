package br.com.theexchange.presentation.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.theexchange.R
import kotlinx.android.synthetic.main.alert_dialog_custom.*

class AlertDialogCustom(mContext: Context) : AlertDialog(mContext) {

    private var isLoadingDialog = false

    private val buttonNegative: Button by lazy { buttonNegativeDialog }
    private val buttonPositive: Button by lazy { buttonPositiveDialog }
    private val textViewTitle: TextView by lazy { textViewDialogTitle }
    private val textViewMessage: TextView by lazy { textViewDialogMessage }

    private var postiveListener: DialogInterface.OnClickListener? = null

    private var negativeListener: DialogInterface.OnClickListener? = null

    private var title = ""
    private var message = ""
    private var buttonPositiveText: String = ""
    private var buttonNegativeText: String = ""


    override fun onStart() {
        super.onStart()

        if (isLoadingDialog) {
            setupLoading()
        } else {
            setup()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_dialog_custom)
    }

    private fun setup() {
        textViewTitle.text = title

        textViewMessage.text = message

        if(!title.isNullOrBlank()){
            textViewTitle.visibility = View.VISIBLE
        }

        if(!message.isNullOrBlank()){
            textViewMessage.visibility = View.VISIBLE
        }

        if (!buttonPositiveText.isNullOrBlank()) {
            buttonPositive.setOnClickListener {
                postiveListener?.onClick(this, DialogInterface.BUTTON_POSITIVE); dismiss()
            }
            buttonPositive.text = buttonPositiveText
        }
        else{
            buttonPositive.visibility = View.GONE
        }
        if (!buttonNegativeText.isNullOrBlank()) {
            buttonNegative.setOnClickListener {
                negativeListener?.onClick(this, DialogInterface.BUTTON_POSITIVE); dismiss()
            }
            buttonNegative.text = buttonNegativeText
        }
        else{
            buttonNegative.visibility = View.GONE
        }
    }

    private fun setupLoading() {
        this.setContentView(R.layout.alert_dialog_custom_loading)
        this.window.setBackgroundDrawable(null);
    }

    private fun setIsLoadingDialog() {
        this.isLoadingDialog = true
    }

    class Builder(context: Context) {

        private val alertDialog = AlertDialogCustom(context)

        fun show(): Builder? {
            this.alertDialog.show()
            return this
        }

        fun setTitle(title: String): Builder? {
            alertDialog.title = title
            return this
        }

        fun setMessage(message: String):Builder? {
            alertDialog.message = message
            return this
        }

        fun setOnClickPositiveButton(text: String, onClickListener: DialogInterface.OnClickListener?): Builder?{
            alertDialog.postiveListener = onClickListener
            alertDialog.buttonPositiveText = text
            return this
        }

        fun setOnClickNegative(text: String, onClickListener: DialogInterface.OnClickListener?): Builder?{
            alertDialog.negativeListener = onClickListener
            alertDialog.buttonNegativeText = text
            return this
        }


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