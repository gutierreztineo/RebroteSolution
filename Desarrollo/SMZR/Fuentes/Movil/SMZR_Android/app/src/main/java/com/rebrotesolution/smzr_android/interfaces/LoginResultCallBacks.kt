package com.rebrotesolution.smzr_android.interfaces

import androidx.lifecycle.LiveData
import com.rebrotesolution.smzr_android.models.Usuario

interface LoginResultCallBacks {

    fun onStarted()
    fun onSuccess(usuario: Usuario)
    fun onError( message: String)
    fun onRegister()
    fun onRecovery()
}