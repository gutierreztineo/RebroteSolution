package com.rebrotesolution.smzr_android.interfaces

interface LoginResultCallBacks {

    fun onSuccess(message : String)
    fun onError( message: String)
    fun onRegister()
}