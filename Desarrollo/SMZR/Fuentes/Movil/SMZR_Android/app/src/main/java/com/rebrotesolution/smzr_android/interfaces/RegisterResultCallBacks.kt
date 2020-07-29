package com.rebrotesolution.smzr_android.interfaces

import com.rebrotesolution.smzr_android.models.Persona

interface RegisterResultCallBacks {

    fun valid(data: Map<String,String>)

    fun invalid(message: String)
}