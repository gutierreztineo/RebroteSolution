package com.rebrotesolution.smzr_android.interfaces

interface FormularioMalestarResultCallBacks {

    fun incomplete(message: String)
    fun complete(send: Map<String, Int>)
}