package com.rebrotesolution.smzr_android.ui.extras

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.rebrotesolution.smzr_android.R

class LoadingDialog (
    private var activity: Activity
){
    private lateinit var  dialog: AlertDialog

    fun start(){
        val builder =  AlertDialog.Builder(activity)
        var dialogView = activity.layoutInflater.inflate(R.layout.custom_dialog_loading,null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }
}