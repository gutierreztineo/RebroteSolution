package com.rebrotesolution.smzr_android.interfaces

interface RegisterResultCallBacks {

    fun valid(data: Map<String,String>)

    fun invalid(message: String)

    fun onSuccess(obj: Any)

    fun onError(message:String)

    fun onStarted()
}