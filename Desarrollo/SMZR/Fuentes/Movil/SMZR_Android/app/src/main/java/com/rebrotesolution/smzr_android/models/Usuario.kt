package com.rebrotesolution.smzr_android.models

import android.text.TextUtils
import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class Usuario(

    private var username:String,
    private var password:String,
    private var id_usuario: Int?,
    private var token: String?

) : BaseObservable() {

    val isDataValid: Boolean
    get() =  getPassword().length >= 5

    val isDataComplete: Boolean
    get() = (!TextUtils.isEmpty(getUsername()) && !TextUtils.isEmpty(getPassword()) )

    fun getPassword() : String {
        return password
    }

    fun getUsername() : String {
        return username
    }

    fun setPassword(password : String ){
        this.password = password
    }

    fun setUsername(username : String ){
        this.username = username
    }

    fun getIdUsuario(): Int{
        return id_usuario!!
    }

}