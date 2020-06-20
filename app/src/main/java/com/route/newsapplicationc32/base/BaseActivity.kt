package com.route.notesapplicationc32.Base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by Mohamed Nabil Mohamed on 6/10/2020.
 * m.nabil.fci2015@gmail.com
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var activity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
    }

    fun showMessage(
        title: String?, message: String?,
        posActionString: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionString: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        isCancelable: Boolean = true
    ) {

        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posActionString, posAction)
            .setNegativeButton(negActionString, negAction)
            .setCancelable(isCancelable)
        builder.show()
    }

    fun showMessage(
        title: Int? = null, message: Int? = null,
        posActionString: Int? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionString: Int? = null,
        negAction: DialogInterface.OnClickListener? = null,
        isCancelable: Boolean = true
    ) {

        val builder = AlertDialog.Builder(this)
        if (title != null)
            builder.setTitle(title)
        if (message != null)
            builder.setMessage(message)
        if (posActionString != null)
            builder.setPositiveButton(posActionString, posAction)
        if (negActionString != null)
            builder.setNegativeButton(negActionString, negAction)

        builder.setCancelable(isCancelable)

        builder.show()
    }

    var progressDialog: ProgressDialog? = null
    fun showLoadingDialog(message: String?): ProgressDialog {
        val dialog = ProgressDialog(this)
        progressDialog = dialog
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }

    fun hideLoadingDialge() {
        if (progressDialog?.isShowing == true)
            progressDialog?.dismiss()
    }
}